/*
 * Copyright (c) 2012-2014 The original author or authors
 * ------------------------------------------------------
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Apache License v2.0 which accompanies this distribution.
 *
 * The Eclipse Public License is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * The Apache License v2.0 is available at
 * http://www.opensource.org/licenses/apache2.0.php
 *
 * You may elect to redistribute this code under either of these licenses.
 */
package cn.chason678.mqtt.moquette.proto.messages;

/**
 *
 * @author andrea
 */
public class PubRelMessage extends MessageIDMessage {
    
    public PubRelMessage() {
        m_messageType = AbstractMessage.PUBREL;
        m_qos = QOSType.LEAST_ONE;
    }

//    @Override
//    public void setQos(QOSType qos) {
//        super.setQos(qos);
//        if(qos != QOSType.LEAST_ONE)
//            System.out.println("PUBREL QoS must be 1 !!");
//    }


    @Override
    public String toString() {
        return "PUBREL: "+ getMessageID();
    }
}
