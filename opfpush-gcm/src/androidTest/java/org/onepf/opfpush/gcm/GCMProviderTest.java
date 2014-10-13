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

package org.onepf.opfpush.gcm;

import android.os.Build;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.onepf.opfpush.PushProvider;
import org.onepf.opfpush.gcm.shadow.ShadowGooglePlayServiceUtil;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Kirill Rozov
 * @since 10/8/14.
 */

@Config(
        emulateSdk = Build.VERSION_CODES.JELLY_BEAN_MR2,
        shadows = ShadowGooglePlayServiceUtil.class,
        reportSdk = Build.VERSION_CODES.JELLY_BEAN
)
@RunWith(RobolectricTestRunner.class)
public class GCMProviderTest extends GCMProviderTestBase {

    @Test
    public void testCheckAvailable_LastGMSInstalled() throws Exception {
        addLatestGMSServiceApp();
        PushProvider provider = new GCMProvider(Robolectric.application, TestConstants.SENDER_ID);
        assertTrue(provider.isAvailable());
        removeGMCServiceApp();
    }

    @Test
    public void testCheckAvailable_NoGMS() throws Exception {
        PushProvider provider = new GCMProvider(Robolectric.application, TestConstants.SENDER_ID);
        assertFalse(provider.isAvailable());
    }

    @Test
    public void testMultiSenderIDsCreate() throws Exception {
        String[] sendersId = {"s1", "s2", "s3"};
        GCMProvider provider
                = new GCMProvider(Robolectric.application,
                sendersId[0], Arrays.copyOfRange(sendersId, 1, sendersId.length));
        assertArrayEquals(sendersId, provider.getSenderIDs());
    }

    @Test
    public void testSingleSenderIDsCreate() throws Exception {
        String senderId = "s1";
        GCMProvider provider = new GCMProvider(Robolectric.application, senderId);
        assertArrayEquals(new String[]{senderId}, provider.getSenderIDs());
    }
}