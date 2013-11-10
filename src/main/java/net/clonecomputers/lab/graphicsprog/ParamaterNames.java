package net.clonecomputers.lab.graphicsprog;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ParamaterNames {
	public String[] value();
}
