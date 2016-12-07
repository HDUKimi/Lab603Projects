package com.horstmann.violet.application.gui.util.wujun.SD2AutomataForPlatform.util;

import java.util.ArrayList;
import java.util.List;

import org.omg.PortableServer.POAManagerPackage.AdapterInactive;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
public class ActivationBarNodeConverter implements Converter {

	@Override
	public boolean canConvert(Class clazz) {
		// TODO Auto-generated method stub
		return clazz.equals(ActivationBarNode.class);
	}

	@Override
	public void marshal(Object arg0, HierarchicalStreamWriter arg1, MarshallingContext arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext arg1) {
		ActivationBarNode node = new ActivationBarNode();
		node.id = reader.getAttribute("id");
		
        reader.moveDown();
        Children c = new Children();
        List<ActivationBarNode> nodes = new ArrayList<ActivationBarNode>();
        
        reader.moveDown();
        while(reader.hasMoreChildren()) {
        	
        	System.out.println(reader.getNodeName());
            ActivationBarNode node2 = new ActivationBarNode();
            node2.setId(reader.getAttribute("id"));
            System.out.println(reader.getAttribute("id"));
            nodes.add(node2);
            
            reader.moveUp();
        }
        c.setNodes(nodes);
        node.setC(c);
        return node;
        
	}

}
