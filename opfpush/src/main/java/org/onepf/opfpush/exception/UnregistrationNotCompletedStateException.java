/*
 * Copyright 2012-2014 One Platform Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.onepf.opfpush.exception;

import org.onepf.opfpush.OPFPushHelper;

/**
 * Thrown when invoke {@link OPFPushHelper#register()} while unregistration.
 *
 * @author Roman Savin
 * @since 05.12.14
 */
public class UnregistrationNotCompletedStateException extends OPFIllegalStateException {

    public UnregistrationNotCompletedStateException() {
        this("Can't register while unregistration is running.");
    }

    public UnregistrationNotCompletedStateException(String detailMessage) {
        super(detailMessage);
    }

    public UnregistrationNotCompletedStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnregistrationNotCompletedStateException(Throwable cause) {
        super(cause);
    }
}
