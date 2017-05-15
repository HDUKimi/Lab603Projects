/*
 Violet - A program for editing UML diagrams.

 Copyright (C) 2007 Cay S. Horstmann (http://horstmann.com)
 Alexandre de Pellegrin (http://alexdp.free.fr);

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package com.horstmann.violet.product.diagram.sequence;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

/**
 * The bean info for the CallEdge type.
 */
public class CallEdgeBeanInfo extends SimpleBeanInfo
{
    @Override
    public PropertyDescriptor[] getPropertyDescriptors()
    {
        try
        {
            
        	PropertyDescriptor name = new PropertyDescriptor("name", CallEdge.class);
        	name.setValue("priority", new Integer(1));
        	PropertyDescriptor input = new PropertyDescriptor("input", CallEdge.class);
        	input.setValue("priority", new Integer(2));
        	PropertyDescriptor parameter = new PropertyDescriptor("parameter", CallEdge.class);
        	parameter.setValue("priority", new Integer(3));
        	PropertyDescriptor output = new PropertyDescriptor("output", CallEdge.class);
        	output.setValue("priority", new Integer(4));
        	PropertyDescriptor timing = new PropertyDescriptor("timing", CallEdge.class);
        	timing.setValue("priority", new Integer(5));
        	PropertyDescriptor timereset = new PropertyDescriptor("timereset", CallEdge.class);
        	timereset.setValue("priority", new Integer(6));
        	PropertyDescriptor signal = new PropertyDescriptor("signal", CallEdge.class);
        	signal.setValue("priority", new Integer(7));
        	return new PropertyDescriptor[]
    	            {
    	            	name,
    	            	input,
    	            	parameter,
    	            	output,
    	            	timing,
    	            	timereset,
    	            	signal,
    	            };
            
        }
        catch (IntrospectionException exception)
        {
            return null;
        }
    }
}
