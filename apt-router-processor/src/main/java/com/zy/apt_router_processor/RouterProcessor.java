package com.zy.apt_router_processor;

import com.google.auto.service.AutoService;
import com.zy.apt_router_annotation.ZRoute;

import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
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
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        /**
         * 得到所有包含Route注解的element集合
         */
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(ZRoute.class);
        Map<String,String> map=new HashMap<>();
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
            //设置生成的类名称
            String finalClassName=clazzName+"$$ZRouter";

            try{
                JavaFileObject sourceFile = filer.createSourceFile(packageName + "." + finalClassName);
                Writer writer = sourceFile.openWriter();
                writer.write("package "+packageName+";\n");
                writer.write("public class "+finalClassName+" {\n");
                writer.write("public static Class<?> findTargetClass(String pathName){\n");
                writer.write("if(pathName.equalsIgnoreCase(\""+path+"\")){\n");
                writer.write("return "+clazzName+".class;\n");
                writer.write("}");
                writer.write("return null;\n");
                writer.write("}\n");
                writer.write("}\n");
                writer.close();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }

        if (map.size()==0){
            messager.printMessage(Diagnostic.Kind.NOTE,"activity map is null..");
            return false;
        }


        return true;
    }
}