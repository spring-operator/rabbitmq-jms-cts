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
 * Copyright 2001, 2003 (C) Exoffice Technologies Inc. All Rights Reserved.
 *
 * $Id: SnapshotServer.java,v 1.2 2004/02/02 03:49:20 tanderson Exp $
 */
package org.exolab.jmscts.core.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


/**
 * This class enables the state of a running compliance suite to be
 * captured. It delegates behaviour to the {@link SnapshotService}
 *
 * @version     $Revision: 1.2 $ $Date: 2004/02/02 03:49:20 $
 * @author      <a href="mailto:tma@netspace.net.au">Tim Anderson</a>
 * @see         SnapshotService
 */
class SnapshotServer extends UnicastRemoteObject implements Snapshot {

    /** TODO */
    private static final long serialVersionUID = 1L;
    /**
     * The snapshot service
     */
    private SnapshotService _service = null;

    /**
     * Construct a new instance
     *
     * @param service the snapshot service
     * @throws RemoteException if failed to export object
     */
    public SnapshotServer(SnapshotService service) throws RemoteException {
        if (service == null) {
            throw new IllegalArgumentException("Argument service is null");
        }
        _service = service;
    }

    /**
     * Snapshot the current test suite coverage
     *
     * @param path the directory path to write the coverage report
     * @throws RemoteException if the report cannot be generated
     */
    @Override
    public void snapshot(String path) throws RemoteException {
        try {
            _service.snapshot(path);
        } catch (Exception exception) {
            throw new RemoteException(exception.getMessage(), exception);
        }
    }

}
