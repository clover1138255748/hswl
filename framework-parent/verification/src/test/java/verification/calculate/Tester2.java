/**
 * hongshiwl.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package verification.calculate;

import java.net.URL;
import java.util.Set;

import com.opengroup.hongshi.verify.tool.Tooler;

/**
 * 
 * @author UU
 * @version $Id: Tester2.java, v 0.1 2017年1月6日 下午5:56:46 UU Exp $
 */
public class Tester2 {

    public void test() throws Exception {
        String path = "verification/config";

        Tooler tooler = new Tooler();
        Set<URL> result = tooler.getUrls4Path(path);
        for (URL url : result) {
            String p = url.getPath();
            System.err.println(p);
            int idx = p.lastIndexOf((int) ('/'));
            System.err.println(idx);
            System.err.println(p.substring(idx));
            //            Properties prop = new Properties();
            //            prop.load(url.openConnection().getInputStream());
            //            System.err.println(Arrays.toString(prop.keySet().toArray(new String[0])));
        }

        //        Enumeration<URL> result = ClassLoader.getSystemResources(path);
        //        URL url = ClassLoader.getSystemResource(path);
        //        String protocol = url.getProtocol();
        //        List<URL> result = new ArrayList<URL>();
        //        if (Constant.PROTOCOL_FILE.equals(protocol)) {
        //            File fp = new File(url.getFile());
        //            File[] files = fp.listFiles();
        //            for (File f : files) {
        //                try {
        //                    result.add(f.toURI().toURL());
        //                } catch (MalformedURLException e) {
        //                }
        //            }
        //        }
        //        if (Constant.PROTOCOL_JAR.equals(protocol)) {
        //            JarFile jarFile;
        //            int len = path.length();
        //            try {
        //                jarFile = ((JarURLConnection) url.openConnection()).getJarFile();
        //                Enumeration<JarEntry> jes = jarFile.entries();
        //                while (jes.hasMoreElements()) {
        //                    String name = jes.nextElement().getName();
        //                    if (name.startsWith(path) && name.length() > len) {
        //                        result.add(ClassLoader.getSystemResource(name));
        //                    }
        //                }
        //            } catch (IOException e) {
        //            }
        //        }
        //        for (URL u : result) {
        //            System.out.println(u);
        //        }

        //        Set<URL> rs = new HashSet<URL>();
        //        while (result.hasMoreElements()) {
        //            URL url = result.nextElement();
        //            System.err.println(url);
        //            String protocol = url.getProtocol();
        //            if (Constant.PROTOCOL_JAR.equals(protocol)) {
        //                JarFile jarFile;
        //                int len = path.length();
        //                try {
        //                    jarFile = ((JarURLConnection) url.openConnection()).getJarFile();
        //                    Enumeration<JarEntry> jes = jarFile.entries();
        //                    while (jes.hasMoreElements()) {
        //                        String name = jes.nextElement().getName();
        //                        Enumeration<URL> crs = ClassLoader.getSystemResources(name);
        //                        while (crs.hasMoreElements()) {
        //                            URL cr = crs.nextElement();
        //                            rs.add(cr);
        //                        }
        //                    }
        //                } catch (IOException e) {
        //                }
        //            }
        //        }
        //        System.err.println(rs.size());
        //        Map<Object, Object> map = new HashMap<Object, Object>();
        //        for (URL u : rs) {
        //            Properties p = new Properties();
        //            p.load(u.openStream());
        //            map.putAll(p);
        //            //            System.err.println(p.get("test1"));
        //            System.err.println(u);
        //        }
        //        System.err.println(map.size());
    }
}
