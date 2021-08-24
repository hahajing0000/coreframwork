package com.zy.apt_router_processor;

import com.google.auto.service.AutoService;
import com.zy.apt_router_annotation.ZRoute;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

@AutoService(Processor.class)
public class RouterProcessor extends AbstractProcessor {

    /**
     * 工具类
     */
    private Elements elementsUtils;

    private Filer filer;
    private Messager messager;
    /**
     * 自定义的包名 存放注解生成类
     */
    private String pkgName;
    /**
     * 子模块名称
     */
    private String moduleName;


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        /**
         * 这个注解处理器是给哪个注解使用的
         */
        HashSet<String> supportType=new LinkedHashSet<>(1);
        supportType.add(ZRoute.class.getCanonicalName());
        return supportType;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        //返回java版本
        return SourceVersion.latestSupported();
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        elementsUtils=processingEnvironment.getElementUtils();
        filer=processingEnvironment.getFiler();
        messager = processingEnvironment.getMessager();
        pkgName = processingEnvironment.getOptions().get(ConstantValue.CUSTOM_PACKAGE_NAME);
        moduleName = processingEnvironment.getOptions().get(ConstantValue.MODULE_NAME);

        messager.printMessage(Diagnostic.Kind.NOTE,"moduleName="+moduleName);
        messager.printMessage(Diagnostic.Kind.NOTE,"pkgName="+pkgName);
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        /**
         * 得到所有包含Route注解的element集合
         */
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(ZRoute.class);
        Map<String,String> map=new HashMap<>();
        Map<String,String> sumMap=new HashMap<>();
        for (Element element:
             elements) {

            TypeElement classElement = (TypeElement) element;
            //获取包名和类名
            String fullClassName = classElement.getQualifiedName().toString();
            //获取包名
            String packageName=elementsUtils.getPackageOf(element).getQualifiedName().toString();

            //获取ZRoute注解
            ZRoute annotation = classElement.getAnnotation(ZRoute.class);
            //获取注解的Path属性
            String path = annotation.path();
            map.put(path,fullClassName+".class");

            //获取类简称
            String clazzName=element.getSimpleName().toString();
//            //设置生成的类名称
//            String finalClassName=clazzName+"$$ZRouter";
//
//            try{
//                JavaFileObject sourceFile = filer.createSourceFile(packageName + "." + finalClassName);
//                Writer writer = sourceFile.openWriter();
//                writer.write("package "+packageName+";\n");
//                writer.write("public class "+finalClassName+" {\n");
//                writer.write("public static Class<?> findTargetClass(String pathName){\n");
//                writer.write("if(pathName.equalsIgnoreCase(\""+path+"\")){\n");
//                writer.write("return "+clazzName+".class;\n");
//                writer.write("}");
//                writer.write("return null;\n");
//                writer.write("}\n");
//                writer.write("}\n");
//                writer.close();
//            }
//            catch (Exception ex){
//                ex.printStackTrace();
//            }
        }

        if (map.size()==0){
            messager.printMessage(Diagnostic.Kind.NOTE,"activity map is null..");
            return false;
        }

        Writer writer=null;
        String clsName="ActivityTools$$"+moduleName;
        String pkgname_prefix="com.zy.router";
        try {
            JavaFileObject sourceFile = filer.createSourceFile(pkgname_prefix+"." + clsName);
            writer=sourceFile.openWriter();
            writer.write("package "+pkgname_prefix+";\n");
            writer.write("import "+pkgname_prefix+".ZRouter;\n");
            writer.write("import "+pkgname_prefix+".IRouter;\n");
            writer.write("public class "+clsName+" implements IRouter{\n");
            writer.write("  @Override\n");
            writer.write("  public void putActivity(){\n");
            Iterator<String> iterator = map.keySet().iterator();
            while (iterator.hasNext()){
                String actKey = iterator.next();
                String cls=map.get(actKey);
                writer.write("      ZRouter.getInstance().put(\""+actKey+"\","+cls+");\n");
            }

            writer.write("  }\n");
            writer.write("}\n");

            sumMap.put(moduleName,pkgname_prefix+"."+clsName);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (writer!=null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return true;
    }
}