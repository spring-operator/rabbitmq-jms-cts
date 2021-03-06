/**
 * Redistribution and use of this software and associated documentation
 * ("Software"), with or without modification, are permitted provided
 * that the following conditions are met:
 *
 * 1. Redistributions of source code must retain copyright
 *    statements and notices.  Redistributions must also contain a
 *    copy of this document.
 *
 * 2. Redistributions in binary form must reproduce the
 *    above copyright notice, this list of conditions and the
 *    following disclaimer in the documentation and/or other
 *    materials provided with the distribution.
 *
 * 3. The name "Exolab" must not be used to endorse or promote
 *    products derived from this Software without prior written
 *    permission of Exoffice Technologies.  For written permission,
 *    please contact tma@netspace.net.au.
 *
 * 4. Products derived from this Software may not be called "Exolab"
 *    nor may "Exolab" appear in their names without prior written
 *    permission of Exoffice Technologies. Exolab is a registered
 *    trademark of Exoffice Technologies.
 *
 * 5. Due credit should be given to the Exolab Project
 *    (http://www.exolab.org/).
 *
 * THIS SOFTWARE IS PROVIDED BY EXOFFICE TECHNOLOGIES AND CONTRIBUTORS
 * ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL
 * EXOFFICE TECHNOLOGIES OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Copyright 2001-2004 (C) Exoffice Technologies Inc. All Rights Reserved.
 *
 * $Id: SequencePropertyPopulator.java,v 1.2 2004/01/31 13:44:24 tanderson Exp $
 */
package org.exolab.jmscts.core;

import javax.jms.Message;


/**
 * A message populator that populates the 'JMSXGroupID' and 'JMSXGroupSeq'
 * properties of a message. The sequence is incremented with each invocation,
 * starting at 1.
 *
 * @version     $Revision: 1.2 $ $Date: 2004/01/31 13:44:24 $
 * @author      <a href="mailto:tma@netspace.net.au">Tim Anderson</a>
 */
public class SequencePropertyPopulator implements MessagePopulator {

    /** TODO */
    private static final long serialVersionUID = 1L;

    /**
     * The JMSX group identifier property name
     */
    public static final String GROUP_ID = "JMSXGroupID";

    /**
     * The JMSX sequence number property name
     */
    public static final String GROUP_SEQ = "JMSXGroupSeq";

    /**
     * The group identifier
     */
    private final String _group;

    /**
     * The sequence number
     */
    private int _sequence = 0;


    /**
     * Create an instance of this class, with the default group name to
     * populate the JMSXGroupID property with
     */
    public SequencePropertyPopulator() {
        this("default");
    }

    /**
     * Create an instance of this class, with the group name to populate the
     * JMSXGroupID property with
     *
     * @param group the value to populate the JMSXGroupID property with
     */
    public SequencePropertyPopulator(String group) {
        if (group == null) {
            throw new IllegalArgumentException("Argument 'group' is null");
        }
        _group = group;
    }

    /**
     * Populate the JMSXGroupID and JMSXGroupSeq property of a message.
     * The sequence is incremented with each invocation.
     *
     * @param message the message to populate
     * @throws Exception for any error
     */
    @Override
    public synchronized void populate(Message message) throws Exception {
        message.setStringProperty(GROUP_ID, _group);
        message.setIntProperty(GROUP_SEQ, ++_sequence);
    }

}
