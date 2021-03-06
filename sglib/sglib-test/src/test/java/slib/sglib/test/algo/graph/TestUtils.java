/*

 Copyright or © or Copr. Ecole des Mines d'Alès (2012) 

 This software is a computer program whose purpose is to 
 process semantic graphs.

 This software is governed by the CeCILL  license under French law and
 abiding by the rules of distribution of free software.  You can  use, 
 modify and/ or redistribute the software under the terms of the CeCILL
 license as circulated by CEA, CNRS and INRIA at the following URL
 "http://www.cecill.info". 

 As a counterpart to the access to the source code and  rights to copy,
 modify and redistribute granted by the license, users are provided only
 with a limited warranty  and the software's author,  the holder of the
 economic rights,  and the successive licensors  have only  limited
 liability. 

 In this respect, the user's attention is drawn to the risks associated
 with loading,  using,  modifying and/or developing or reproducing the
 software by the user in light of its specific status of free software,
 that may mean  that it is complicated to manipulate,  and  that  also
 therefore means  that it is reserved for developers  and  experienced
 professionals having in-depth computer knowledge. Users are therefore
 encouraged to load and test the software's suitability as regards their
 requirements in conditions enabling the security of their systems and/or 
 data to be ensured and,  more generally, to use and operate it in the 
 same conditions as regards security. 

 The fact that you are presently reading this means that you have had
 knowledge of the CeCILL license and that you accept its terms.

 */
package slib.sglib.test.algo.graph;

import slib.sglib.io.conf.GDataConf;
import slib.sglib.io.conf.GraphConf;
import slib.sglib.io.loader.GraphLoaderGeneric;
import slib.sglib.io.util.GFormat;
import slib.sglib.model.graph.G;
import slib.sglib.model.impl.repo.URIFactoryMemory;
import slib.utils.ex.SLIB_Exception;

/**
 *
 * @author seb
 */
public class TestUtils {

    /**
     *
     * @param gFormat
     * @param filePath
     * @return
     * @throws SLIB_Exception
     */
    public static G loadTestGraph(GFormat gFormat, String filePath) throws SLIB_Exception {

        System.out.println("Loading graph:::");
        
        GraphConf conf = new GraphConf();
        conf.setUri(URIFactoryMemory.getSingleton().createURI("http://example/"));
        conf.addGDataConf(new GDataConf(gFormat, filePath));
        
        System.out.println("Loading :::");
        G g = GraphLoaderGeneric.load(conf);
        
        System.out.println("info "+g.toString());


        return g;
    }
}
