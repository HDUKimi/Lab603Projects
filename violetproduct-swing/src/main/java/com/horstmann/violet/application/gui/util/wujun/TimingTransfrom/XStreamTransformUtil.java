package com.horstmann.violet.application.gui.util.wujun.TimingTransfrom;

import com.horstmann.violet.application.gui.util.wujun.TimingTransfrom.Lifeline.Schildren;
import com.horstmann.violet.application.gui.util.wujun.TimingTransfrom.Lifeline.Schildren.Horizontalchild;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.basic.DoubleConverter;
import com.thoughtworks.xstream.converters.basic.FloatConverter;
import com.thoughtworks.xstream.converters.basic.IntConverter;
import com.thoughtworks.xstream.converters.basic.LongConverter;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.awt.geom.Line2D.Double;
public class XStreamTransformUtil {

    private final static String TAG = XStreamTransformUtil.class.getSimpleName();
   
    @SuppressWarnings("unchecked")
    public static <T> T toBean(Class<T> type, InputStream is) { 
//       json用 JettisonMappedXmlDriver()  || xml用 DomDriver()
        XStream xStream = new XStream(new DomDriver("UTF-8"));
        xStream.setMode(XStream.NO_REFERENCES);
        xStream.aliasSystemAttribute(null, "class");
        // 在那个类写上 @XStreamConverter(ActivationBarNodeConverter.class)
        // xmStream.registerConverter(new ActivationBarNodeConverter());
        
        // 设置可忽略为在javabean类中定义的界面属性
//        xStream.ignoreUnknownElements();
        xStream.registerConverter(new MyIntCoverter());
        xStream.registerConverter(new MyLongCoverter());
        xStream.registerConverter(new MyFloatCoverter());
        xStream.registerConverter(new MyDoubleCoverter());
        xStream.processAnnotations(type);
        T obj = null;
        try {
            obj = (T) xStream.fromXML(is);
        } catch (Exception e) {
            System.out.println("转换异常" + e);
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                  
                }
            }
        }
        return obj;
    }
    
    
    public static <T> T toBean(Class<T> type, byte[] bytes) { //直接传入byte
        if (bytes == null) return null;
        return toBean(type, new ByteArrayInputStream(bytes));
    }

    private static class MyIntCoverter extends IntConverter {

        @Override
        public Object fromString(String str) {
            int value;
            try {
                value = (Integer) super.fromString(str);
            } catch (Exception e) {
                value = 0;
            }
            return value;
        }

        @Override
        public String toString(Object obj) {
            return super.toString(obj);
        }
    }

    private static class MyLongCoverter extends LongConverter {
        @Override
        public Object fromString(String str) {
            long value;
            try {
                value = (Long) super.fromString(str);
            } catch (Exception e) {
                value = 0;
            }
            return value;
        }

        @Override
        public String toString(Object obj) {
            return super.toString(obj);
        }
    }

    private static class MyFloatCoverter extends FloatConverter {
        @Override
        public Object fromString(String str) {
            float value;
            try {
                value = (Float) super.fromString(str);
            } catch (Exception e) {
                value = 0;
            }
            return value;
        }

        @Override
        public String toString(Object obj) {
            return super.toString(obj);
        }
    }

    private static class MyDoubleCoverter extends DoubleConverter {
        @Override
        public Object fromString(String str) {
            double value;
            try {
                value = (double) super.fromString(str);
            } catch (Exception e) {
                value = 0;
            }
            return value;
        }

        @Override
        public String toString(Object obj) {
            return super.toString(obj);
        }
    }
    
//    private static class MyLin2DDoubleCoverter extends DoubleConverter {
//        @Override
//        public Object fromString(String str) {
//        	java.awt.geom.Line2D.Double value;
//            try {
//                value = (java.awt.geom.Line2D.Double) super.fromString(str);
//                
//            } catch (Exception e) {
//                value = (java.awt.geom.Line2D.Double);
//            }
//            return value;
//        }
//
//        @Override
//        public String toString(Object obj) {
//            return super.toString(obj);
//        }
//    }
}
