/*
 * CDDL HEADER START
 *
 * The contents of this file are subject to the terms of the
 * Common Development and Distribution License (the "License").
 * You may not use this file except in compliance with the License.
 *
 * See LICENSE.txt included in this distribution for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL HEADER in each
 * file and include the License file at LICENSE.txt.
 * If applicable, add the following below this CDDL HEADER, with the
 * fields enclosed by brackets "[]" replaced with your own identifying
 * information: Portions Copyright [yyyy] [name of copyright owner]
 *
 * CDDL HEADER END
 */

/*
 * Copyright (c) 2010, 2022, Oracle and/or its affiliates. All rights reserved.
 */
package org.opengrok.indexer.history;

import org.junit.jupiter.api.Test;
import org.opengrok.indexer.condition.EnabledForRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.opengrok.indexer.condition.RepositoryInstalled.Type.SCCS;

/**
 *
 * @author Lubos Kosco
 */
@EnabledForRepository(SCCS)
class SCCSRepositoryTest {

    /**
     * Test of isRepositoryFor method, of class SCCSRepository.
     */
    private void testIsRepositoryFor(final String fileName, boolean shouldPass) throws IOException {
        File tdir = Files.createTempDirectory("SCCSrepotest" + fileName).toFile();
        File test = new File(tdir, fileName);
        assertTrue(test.mkdirs(), "failed to create directory");
        tdir.deleteOnExit();
        test.deleteOnExit();
        SCCSRepository instance = new SCCSRepository();
        assertEquals(shouldPass, instance.isRepositoryFor(tdir));
    }

    @Test
    void testIsRepositoryForCodemgr1() throws IOException {
        testIsRepositoryFor("Codemgr_wsdata", true);
    }

    @Test
    void testIsRepositoryForCodemgr2() throws IOException {
        testIsRepositoryFor("codemgr_wsdata", true);
    }

    @Test
    void testIsRepositoryForCodemgr3() throws IOException {
        testIsRepositoryFor("SCCS", true);
    }

    @Test
    void testIsRepositoryForCodemgrNot() throws IOException {
        testIsRepositoryFor("NOT", false);
    }
}
