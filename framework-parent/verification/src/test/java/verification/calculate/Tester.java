/**
  * hongshiwl.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package verification.calculate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.beanutils.PropertyUtils;

import com.opengroup.hongshi.verify.VerificationConfigException;

/**
 * 
 * @author UU
 * @version $Id: Tester.java, v 0.1 2017年1月5日 下午2:15:55 UU Exp $
 */
public class Tester {
    public static final char SIGN_LIST = '@';

    public static void main(String[] args) {
        TB1 tb1 = new TB1();
        tb1.setName("tb1Name");
        List<TB2> tb2List = new ArrayList<TB2>();
        tb1.setTb2(tb2List);
        int count = 0;
        //        TB2 tmp = new TB2();
        //        tmp.setName("temp");
        for (int i = 0; i < 2; i++) {
            TB2 tb2 = new TB2();
            //            tb2.setTb2(tmp);
            tb2List.add(tb2);
            tb2.setName("tb2Name" + i);
            Set<TB3> tb3Set = new HashSet<TB3>();
            tb2.setTb3(tb3Set);
            for (int j = 0; j < 3; j++) {
                TB3 tb3 = new TB3();
                tb3Set.add(tb3);
                tb3.setName("tb3Name" + j);
                TB4[] tb4 = new TB4[2];
                tb3.setTb4(tb4);
                for (int k = 0; k < 2; k++) {
                    tb4[k] = new TB4();
                    tb4[k].setName("tb4Name" + count++);
                }
            }
        }
        //        Stack<List<Object>> data = new Stack<List<Object>>();
        //        List<Object> dl = new ArrayList<Object>();
        //        dl.add(tb1);
        //        data.push(dl);
        //
        //        handleList("tb2.@tb2.name", data);
        //        List<Object> rs = data.pop();
        //        for (Object o : rs) {
        //            System.err.println(o);
        //        }

        Stack<List<Object>> data2 = new Stack<List<Object>>();
        List<Object> dl2 = new ArrayList<Object>();
        dl2.add(tb1);
        data2.push(dl2);
        handleList("tb2.[0]tb3.@tb4.[0]name", data2);

        List<Object> rs2 = data2.pop();
        for (Object o : rs2) {
            System.err.println(o);
        }
    }

    @SuppressWarnings("rawtypes")
    private static void handleList(String str, Stack<List<Object>> data) {
        int idx = str.indexOf(SIGN_LIST);
        List<Object> hd = data.pop();
        List<Object> result = new ArrayList<Object>();
        if (idx == -1) {
            for (Object o : hd) {
                if (o != null) {
                    result.add(getObject(o, str));
                }
            }
            data.push(result);
        } else {
            String k = str.substring(0, idx - 1);
            for (Object o : hd) {
                Object sos = null;
                if (o != null) {
                    sos = getObject(o, k);
                }
                if (sos != null) {
                    if (sos.getClass().isArray()) {
                        Object[] os = (Object[]) sos;
                        int count = os.length;
                        for (int i = 0; i < count; i++) {
                            result.add(os[i]);
                        }
                    } else if (sos instanceof Collection) {
                        Collection cs = (Collection) sos;
                        Iterator is = cs.iterator();
                        while (is.hasNext()) {
                            result.add(is.next());
                        }
                    } else {
                        throw new VerificationConfigException("通过表达式[" + str + "]获取的值不是一个数组或list");
                    }
                }
            }
            data.push(result);
            handleList(str.substring(idx + 1), data);
        }
        //        return data.pop();
    }

    private static Object getObject(Object data, String key) {
        try {
            if (data != null) {
                return PropertyUtils.getNestedProperty(data, key);
            }
        } catch (NestedNullException e) {
        } catch (Exception e) {
            throw new VerificationConfigException(e, "获取匹配值失败[" + key + "]");
        }
        return null;
    }
}
