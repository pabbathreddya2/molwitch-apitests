/*
 * NCATS-WITCH-APITESTS
 *
 * Copyright 2019 NIH/NCATS
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package gov.nih.ncats.molwitch.tests;

import gov.nih.ncats.molwitch.Chemical;
import gov.nih.ncats.molwitch.TetrahedralChirality;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Created by katzelda on 7/30/19.
 */
public class TestTetrahedrals {

    @Test
    public void tetrahedralWithNcenterNotReal() throws IOException{
        try(InputStream in = new BufferedInputStream((this.getClass().getResourceAsStream("/molFiles/tetrahedrals.mol")))) {

            Chemical c = Chemical.parseMol(in);

            List<TetrahedralChirality> tetrahedralChiralityList = c.getTetrahedrals();
            tetrahedralChiralityList.stream().forEach(System.out::println);
            assertEquals(2, tetrahedralChiralityList.size());
            Set<String> expected = tetrahedralChiralityList.stream().map(tet -> tet.getCenterAtom().getSymbol()).collect(Collectors.toSet());

            assertEquals(Collections.singleton("C"), expected);
        }
    }

}