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

package org.onepf.opfpush;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

import org.onepf.opfpush.exception.OPFPushException;
import org.onepf.opfpush.util.PackageUtils;

/**
 * Base class for create {@code PushProvider}.
 *
 * @author Kirill Rozov
 * @author Roman Savin
 * @since 05.09.14
 */
public abstract class BasePushProvider implements PushProvider {

    @NonNull
    private final Context appContext;

    @NonNull
    private final String name;

    @NonNull
    private final String hostAppPackage;

    /**
     * Base constructor for subclass.
     *
     * @param context        Any instance of {@code Context}.
     * @param name           Provider name.
     * @param hostAppPackage Package of application that handle push message from server
     *                       and deliver it to applications.
     */
    protected BasePushProvider(@NonNull final Context context,
                               @NonNull final String name,
                               @NonNull final String hostAppPackage) {
        this.appContext = context.getApplicationContext();
        this.name = name;
        this.hostAppPackage = hostAppPackage;
    }

    @Override
    public boolean isAvailable() {
        return PackageUtils.isInstalled(appContext, hostAppPackage);
    }

    @Override
    public String toString() {
        return name + "(hostAppPackage='" + hostAppPackage + ')';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final BasePushProvider that = (BasePushProvider) o;
        return name.equals(that.name);
    }

    @Override
    public boolean checkManifest() {
        OPFPushLog.methodD(BasePushProvider.class, "checkManifest");
        return checkPermission(appContext, android.Manifest.permission.INTERNET);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @NonNull
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void onUnavailable() {
    }

    @NonNull
    @Override
    public String getHostAppPackage() {
        return hostAppPackage;
    }

    @Override
    public void onRegistrationInvalid() {
    }

    /**
     * Verify is manifest contains permission.
     *
     * @param context    Any instance of {@code Context}.
     * @param permission Permission for verify.
     */
    protected static boolean checkPermission(@NonNull final Context context,
                                             @NonNull final String permission) {
        OPFPushLog.methodD(BasePushProvider.class, "checkPermission", context, permission);

        switch (context.getPackageManager().checkPermission(permission, context.getPackageName())) {
            case PackageManager.PERMISSION_GRANTED:
                return true;

            default:
                throw new OPFPushException("Your manifest doesn't contain permission '"
                        + permission + ".' Check your AndroidManifest.xml.");
        }
    }

    /**
     * Get {@code Context} instance.
     */
    @NonNull
    protected Context getContext() {
        return appContext;
    }
}
