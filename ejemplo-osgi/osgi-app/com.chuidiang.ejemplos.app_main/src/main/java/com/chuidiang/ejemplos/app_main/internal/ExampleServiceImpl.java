package com.chuidiang.ejemplos.app_main.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.chuidiang.ejemplos.app_main.ExampleService;

/**
 * Internal implementation of our example OSGi service
 */
public final class ExampleServiceImpl
    implements ExampleService
{
    // implementation methods go here...

    public String scramble( String text )
    {
        List charList = new ArrayList();

        char[] textChars = text.toCharArray();
        for( int i = 0; i < textChars.length; i++ )
        {
            charList.add( new Character( textChars[i] ) );
        }

        Collections.shuffle( charList );

        char[] mixedChars = new char[text.length()];
        for( int i = 0; i < mixedChars.length; i++ )
        {
            mixedChars[i] = ( (Character) charList.get( i ) ).charValue();
        }

        return new String( mixedChars );
    }
    
    public void start(){
       System.out.println("app-main starting");
    }
    
    public void stop(){
       System.out.println("app-main stoping");
    }
    
    public void addPlugin(com.chuidiang.ejemplos.plugin1.ExampleService plugin){
       System.out.println("plugin1 added");
    }
    
    public void removePlugin(com.chuidiang.ejemplos.plugin1.ExampleService plugin){
       System.out.println("plugin1 removed");
    }

}

