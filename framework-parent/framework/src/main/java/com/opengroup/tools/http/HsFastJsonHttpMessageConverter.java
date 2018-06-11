package com.opengroup.tools.http;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.opengroup.tools.string.StringUtil;

/**
 * ֧��jsonP�Ķ���
 * 
 * @author jin
 * @version $Id: HsFastJsonHttpMessageConverter.java, v 0.1 2015��6��15�� ����4:49:30 jin Exp $
 */
public class HsFastJsonHttpMessageConverter extends FastJsonHttpMessageConverter {

    @Override
    protected void writeInternal(Object obj, HttpOutputMessage outputMessage) throws IOException,
                                                                             HttpMessageNotWritableException {
        checkResult(obj);
        HttpServletRequest request = ServletContextHolder.getRequest();
        String callback = request.getParameter("callback");
        OutputStream out = outputMessage.getBody();
        try {
            if (!StringUtil.isBlank(callback)) {
                String text = callback
                              + "("
                              + JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue,
                                  SerializerFeature.DisableCircularReferenceDetect) + ")";
                byte[] bytes = text.getBytes(getCharset());
                out.write(bytes);
            } else {
                if (((CommonResponse) obj).querySerializerFeatures() != null){
                    setFeatures(((CommonResponse) obj).querySerializerFeatures());
                }
                super.writeInternal(obj, outputMessage);
            }
        } finally {
            out.flush();
            out.close();
        }
    }

    /**
     * �������Ƿ���CommonResponse ��׼ ���ض���
     * 
     * @param obj
     */
    private void checkResult(Object obj) {
        if (obj != null && !(obj instanceof CommonResponse)) {
            throw new RuntimeException("���ز���������CommonResponse��ʵ��");
        }
    }
}
