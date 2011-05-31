/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ivr.workflow.xml;

import com.ivr.workflow.Action;
import com.ivr.workflow.Instruction;
import com.ivr.workflow.Option;
import com.ivr.workflow.Select;
import com.ivr.workflow.Step;
import com.ivr.workflow.impl.DefaultAction;
import com.ivr.workflow.impl.DefaultOption;
import com.ivr.workflow.impl.DefaultSelect;
import com.ivr.workflow.impl.DefaultStep;
import com.ivr.workflow.impl.instruction.Answer;
import com.ivr.workflow.impl.instruction.GetData;
import com.ivr.workflow.impl.instruction.HangUp;
import com.ivr.workflow.impl.instruction.PlayAudio;
import com.ivr.workflow.impl.instruction.PlayDigits;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author swoosh
 */
public class DefaultXmlReader implements WorkflowReader{
    private Logger logger;
    private String workflowName;
    private String workflowDescription;
    private String startStep;
    private Map<String, Step> steps;

    private Document xmlDom;
    private DocumentBuilder db;

    public DefaultXmlReader(){
        super();
        this.logger = Logger.getLogger(DefaultXmlReader.class);
    }

    public void load(File file) {
        try {
            this.load(new FileInputStream(file));
        } catch (FileNotFoundException ex) {
            this.logger.error("Unable to load file: " + file.getAbsolutePath(), ex);
        }
    }

    public void load(InputStream is) {
        try {
            db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            xmlDom = db.parse(is);
            steps = new HashMap<String, Step>();

            NodeList topNodes = xmlDom.getChildNodes();
            for(int i = 0; i < topNodes.getLength(); i++){
                Node topNode = topNodes.item(i);
                
                if("workflow".equalsIgnoreCase(topNode.getNodeName())){
                    NodeList stepsAndDesc = topNode.getChildNodes();
                    NamedNodeMap attributeMap = topNode.getAttributes();
                    this.workflowName = attributeMap.getNamedItem("name").getNodeValue();
                    this.startStep = attributeMap.getNamedItem("start").getNodeValue();

                    for(int j = 0; j < stepsAndDesc.getLength(); j++){
                        Node stepOrDesc = stepsAndDesc.item(j);

                        if("description".equalsIgnoreCase(stepOrDesc.getNodeName())){
                            this.workflowDescription = stepOrDesc.getTextContent();
                        }else if("step".equalsIgnoreCase(stepOrDesc.getNodeName())){
                            Step step = parseStep(stepOrDesc);
                            this.steps.put(step.getName(), step);
                        }
                    }
                }
            }
            
        } catch (SAXException ex) {
            this.logger.error("Error while processing workflow definition", ex);
        } catch (IOException ex) {
            this.logger.error("Error while processing workflow definition", ex);
        } catch (ParserConfigurationException ex) {
            this.logger.error("Error while processing workflow definition", ex);
        }
    }

    public Map<String, Step> getSteps() {
        return this.steps;
    }

    public String getWorkflowName() {
        return this.workflowName;
    }

    public String getWorkflowDescription(){
        return this.workflowDescription;
    }

    public String getStartStep(){
        return this.startStep;
    }

    private Step parseStep(Node stepOrDesc) {
        Step step = new DefaultStep();
        NamedNodeMap attributeMap = stepOrDesc.getAttributes();
        if(attributeMap.getNamedItem("name")!= null){
            step.setName(attributeMap.getNamedItem("name").getNodeValue());
        }
        if(attributeMap.getNamedItem("lang") != null){
            step.setLang(attributeMap.getNamedItem("lang").getNodeValue());
        }
        
        NodeList nodes = stepOrDesc.getChildNodes();

        for(int i=0; i<nodes.getLength(); i++){
            Node node = nodes.item(i);
            String nodeName = node.getNodeName();
            if("start-action".equalsIgnoreCase(nodeName)){
                step.setStartActionName(node.getTextContent());
            }else if("select".equalsIgnoreCase(nodeName)){
                step.setSelect(parseSelect(node));
            }else if("global-params".equalsIgnoreCase(nodeName)){
                Map<String, String> params = parseParams(node);
                step.setParams(params);
            }else if("actions".equalsIgnoreCase(nodeName)){
                NodeList actionNodes = node.getChildNodes();
                for(int j=0; j<actionNodes.getLength(); j++){
                    Node actionNode = actionNodes.item(j);
                    if("action".equalsIgnoreCase(actionNode.getNodeName())){
                        Action action = parseAction(actionNode);
                        step.addAction(action);
                    }
                }
            }
        }
        
        return step;
    }

    private Select parseSelect(Node selectNode) {
        Select select = new DefaultSelect();
        NamedNodeMap attributeMap = selectNode.getAttributes();
        if(attributeMap.getNamedItem("length")!= null){
            select.setLength(Integer.parseInt(attributeMap.getNamedItem("length").getNodeValue()));
        }
        if(attributeMap.getNamedItem("ends-with")!= null){
            select.setEndsWith(attributeMap.getNamedItem("ends-with").getNodeValue());
        }
        if(attributeMap.getNamedItem("timeout-action")!= null){
            select.setTimeoutAction(attributeMap.getNamedItem("timeout-action").getNodeValue());
        }
        if(attributeMap.getNamedItem("invalid-select-action")!= null){
            select.setInvalidSelectAction(attributeMap.getNamedItem("invalid-select-action").getNodeValue());
        }
        if(attributeMap.getNamedItem("repeat-key")!= null){
            select.setRepeatKey(attributeMap.getNamedItem("repeat-key").getNodeValue());
        }
        if(attributeMap.getNamedItem("repeat-action")!= null){
            select.setRepeatAction(attributeMap.getNamedItem("repeat-action").getNodeValue());
        }

        NodeList optionNodes = selectNode.getChildNodes();
        List<Option> options = new ArrayList<Option>();
        for(int i=0; i<optionNodes.getLength(); i++){
            Node optionNode = optionNodes.item(i);
            if("option".equalsIgnoreCase(optionNode.getNodeName())){
                Option option = parseOption(optionNode);
                options.add(option);
            }
        }
        select.setOptions(options);
        
        return select;
    }

    private Action parseAction(Node actionNode) {
        Action action = new DefaultAction();
        NamedNodeMap attributeMap = actionNode.getAttributes();
        if(attributeMap.getNamedItem("name")!= null){
            action.setName(attributeMap.getNamedItem("name").getNodeValue());
        }

        Map<String, String> params = null;
        NodeList actionChildren = actionNode.getChildNodes();
        List<Instruction> insts = new ArrayList<Instruction>();
        for(int i=0; i<actionChildren.getLength(); i++){
            Node childNode = actionChildren.item(i);
            if(!"#text".equalsIgnoreCase(childNode.getNodeName())){
                if("params".equalsIgnoreCase(childNode.getNodeName())){
                    params = parseParams(childNode);
                }else if("goto".equalsIgnoreCase(childNode.getNodeName())){
                    parseGoto(action, childNode);
                }else{
                    Instruction inst = parseInstruction(childNode);
                    insts.add(inst);
                }
            }
        }
        
        if(params == null){
            params = new HashMap<String, String>();
        }
        action.setParams(params);
        action.setInstructions(insts);
        return action;
    }

    private void parseGoto(Action action, Node gotoNode){
        NodeList nodes = gotoNode.getChildNodes();
        for(int i=0; i<nodes.getLength(); i++){
            Node child = nodes.item(i);
            if("action-name".equalsIgnoreCase(child.getNodeName())){
                action.setNextAction(child.getTextContent());
            }else if("step-name".equalsIgnoreCase(child.getNodeName())){
                action.setNextStep(child.getTextContent());
            }else if("step-lang".equalsIgnoreCase(child.getNodeName())){
                action.setNextStepLang(child.getTextContent());
            }
        }
    }

    private Option parseOption(Node optionNode) {
        Option option = new DefaultOption();
        NamedNodeMap attributeMap = optionNode.getAttributes();
        if(attributeMap.getNamedItem("name")!= null){
            option.setName(attributeMap.getNamedItem("name").getNodeValue());
        }
        if(attributeMap.getNamedItem("onselect")!= null){
            option.setOnSelect(attributeMap.getNamedItem("onselect").getNodeValue());
        }
        option.setValue(optionNode.getTextContent());
        
        return option;
    }

    private Map<String, String> parseParams(Node node) {
        Map<String, String> params = new HashMap<String, String>();
        NodeList paramNodes = node.getChildNodes();
        for(int i=0; i<paramNodes.getLength(); i++){
            Node paramNode = paramNodes.item(i);
            if(!"#text".equalsIgnoreCase(paramNode.getNodeName())){
                params.put(paramNode.getNodeName(), paramNode.getTextContent());
            }
        }
        return params;
    }

    private Instruction parseInstruction(Node childNode) {
        Instruction inst = null;
        Map params = new HashMap();;
        if("play".equalsIgnoreCase(childNode.getNodeName())){
            inst = new PlayAudio();
            params.put("audioFile", childNode.getTextContent());
        }else if("get-data".equalsIgnoreCase(childNode.getNodeName())){
            inst = new GetData();
            params.put("audioFile", childNode.getTextContent());
        }else if("hangup".equalsIgnoreCase(childNode.getNodeName())){
            inst = new HangUp();
        }else if("answer".equalsIgnoreCase(childNode.getNodeName())){
            inst = new Answer();
        }else if("play-digits".equalsIgnoreCase(childNode.getNodeName())){
            inst = new PlayDigits();
        }
        inst.setParams(params);
        return inst;
    }
}
