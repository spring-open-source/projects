package com.mycompany.javamustache;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.MustacheFactory;

/**
 *
 * @author hardiku
 */
public class MustacheUtil
{

    public static MustacheFactory getMustacheFactory()
    {
        return new DefaultMustacheFactory();
    }
}
