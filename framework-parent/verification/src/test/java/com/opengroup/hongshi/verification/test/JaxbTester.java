/**
 * hongshiwl.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package com.opengroup.hongshi.verification.test;

import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.opengroup.hongshi.verify.config.jaxb.BaseRuleType;
import com.opengroup.hongshi.verify.config.jaxb.Validation;
import com.opengroup.hongshi.verify.config.jaxb.Validations;

/**
 * 
 * @author UU
 * @version $Id: JaxbTester.java, v 0.1 2017年1月16日 下午4:49:17 UU Exp $
 */
public class JaxbTester {

    public static void main(String[] args) throws Exception {
        JAXBContext jc = JAXBContext
            .newInstance(com.opengroup.hongshi.verify.config.jaxb.ObjectFactory.class);
        InputStream in = Thread.currentThread().getContextClassLoader()
            .getResourceAsStream("verification/testjaxb.xml");
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        Validations vs = (Validations) unmarshaller.unmarshal(in);
        List<Validation> validations = vs.getValidation();
        System.err.println(validations.size());

        Validation validation = validations.get(0);
        System.err.println(validation.getExtra());
        System.err.println(validation.getExtras().getExtra().size());
        System.err.println(validation.getRefrule().getRules().size());
        List<BaseRuleType> ls = validation.getRules();
        for (BaseRuleType rt : ls) {
            System.err.println("==========");
            System.err.println(rt.getId());
            System.err.println(rt.getCode());
            System.err.println(rt.getExtra());
            System.err.println(rt.getMessage());
            System.err.println(rt.getSource());
            System.err.println(rt.getTarget());
            System.err.println(rt.isNot());
            System.err.println("==========");
        }
    }
}
