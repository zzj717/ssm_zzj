package com.fh.annotaction;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) // 应用于属性（包括枚举中的常量）
@Retention(RetentionPolicy.RUNTIME) //由JVM 加载，包含在类文件中，在运行时可以被获取到
public @interface DoenExcel {

}
