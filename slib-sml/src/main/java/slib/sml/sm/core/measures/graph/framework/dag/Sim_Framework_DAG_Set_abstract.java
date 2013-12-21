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
package slib.sml.sm.core.measures.graph.framework.dag;

import java.util.Set;
import org.openrdf.model.URI;

import slib.sml.sm.core.measures.Sim_Groupwise_Direct;
import slib.sml.sm.core.measures.graph.pairwise.dag.Sim_Pairwise_DAG;
import slib.sml.sm.core.engine.SM_Engine;
import slib.sml.sm.core.utils.SMconf;
import slib.utils.ex.SLIB_Exception;

/**
 * Abstract class used to facilitate implementation of set based measures.
 *
 * @author Sébastien Harispe
 */
public abstract class Sim_Framework_DAG_Set_abstract implements Sim_Pairwise_DAG, Sim_Groupwise_Direct {

    @Override
    public double sim(URI a, URI b, SM_Engine c, SMconf conf) throws SLIB_Exception {

        Set<URI> ancA = c.getAncestorsInc(a);
        Set<URI> ancB = c.getAncestorsInc(b);

        return sim(ancA, ancB, conf);
    }

    @Override
    public double sim(Set<URI> setA, Set<URI> setB, SM_Engine c, SMconf conf) throws SLIB_Exception {
        Set<URI> ancA = c.getAncestorsInc(setA);
        Set<URI> ancB = c.getAncestorsInc(setB);

        return sim(ancA, ancB, conf);
    }

    /**
     * This provide a way to compare the two sets of concepts which have been
     * extended considering inference based on the taxonomic hierarchy. In other
     * words, each vertex of the set already contains all its ancestors in the
     * set.
     *
     * @param ancA the first set
     * @param ancB the second set
     * @param conf the configuration to apply to compute the semantic similarity
     * @return the similarity of the two sets.
     * @throws SLIB_Exception
     */
    public abstract double sim(Set<URI> ancA, Set<URI> ancB, SMconf conf) throws SLIB_Exception;
}
