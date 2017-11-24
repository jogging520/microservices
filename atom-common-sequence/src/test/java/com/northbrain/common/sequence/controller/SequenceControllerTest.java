package com.northbrain.common.sequence.controller;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class SequenceControllerTest
{
    @Inject
    private SequenceController sequenceController;

    @Test
    public void readNextGlobalValue() throws Exception
    {
        // 判断注入是否成功
        Assert.assertNotNull(sequenceController);

    }

    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(SequenceController.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
