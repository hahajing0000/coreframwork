package com.zy.apt_router_processor;

import com.google.auto.service.AutoService;
import com.zy.apt_router_annotation.Route;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;

@AutoService(Processor.class)
@SuppressWarnings("unused")
public class RouterProcessor extends AbstractProcessor {

    /**
     * 工具类
     */
    private Elements elementsUtils;
    

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        /**
         * 这个注解处理器是给哪个注解使用的
         */
        HashSet<String> supportType=new LinkedHashSet<>(1);
        supportType.add(Route.class.getCanonicalName());
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
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        /**
         * 得到所有包含Route注解的element集合
         */
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(Route.class);
        for (Element element:
             elements) {

            VariableElement variableElement= (VariableElement) element;
            TypeElement classElement = (TypeElement) variableElement.getEnclosingElement();
            //获取包名和类名
            String fullClassName = classElement.getQualifiedName().toString();

            Route annotation = variableElement.getAnnotation(Route.class);
            String path = annotation.path();

        }
        return false;
    }
}