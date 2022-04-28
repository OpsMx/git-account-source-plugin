/*
 * Copyright 2022 OpsMx, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.git.spinnaker.plugin.registration;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

class GoogleCredentialsDefinitionSourceTest {

    @Test
    void testGetCredentialsDefinitions() {
        GoogleCredentialsDefinitionSource googleCredentialsDefinitionSource = new GoogleCredentialsDefinitionSource();
        GitAccountsStatus accountsStatus = mock(GitAccountsStatus.class);
        ReflectionTestUtils.setField(googleCredentialsDefinitionSource, "accountsStatus", accountsStatus);
        when(accountsStatus.fetchAccounts()).thenReturn(true);
        googleCredentialsDefinitionSource.getCredentialsDefinitions();
        verify(accountsStatus, times(1)).fetchAccounts();
    }
}
