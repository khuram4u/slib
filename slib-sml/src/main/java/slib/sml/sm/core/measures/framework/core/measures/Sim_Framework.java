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
package slib.sml.sm.core.measures.framework.core.measures;

import slib.sglib.model.graph.elements.V;
import slib.sml.sm.core.engine.SM_Engine;
import slib.sml.sm.core.measures.framework.core.engine.GraphRepresentation;
import slib.sml.sm.core.measures.framework.core.engine.RepresentationOperators;
import slib.sml.sm.core.utils.SMconf;
import slib.utils.ex.SLIB_Exception;

/**
 * Interface defining the methods shared by similarity measures based on the
 * abstract framework. In short, those measures are based on a abstract layer
 * defining the elements to compare, e.g. concept or group of concepts, as
 * (graph) representations and the operators which can be used to perform the
 * operations on which rely the abstract measure in use i.e. assessment of the
 * commonality and difference of the compared elements.
 *
 * @author Harispe Sébastien
 */
public interface Sim_Framework {

    /**
     * Assess the similarity of two elements based on the specified
     * configuration.
     *
     * @param a the first element to compare
     * @param b the second element to compare
     * @param engine the engine used to perform semantic measures computation
     * @param operators the operators on which rely the abstract measure
     * @param conf the configuration
     * @return the similarity score
     * @throws SLIB_Exception
     */
    public double sim(V a,
            V b,
            SM_Engine engine,
            RepresentationOperators operators,
            SMconf conf) throws SLIB_Exception;

    /**
     *
     * Assess the similarity of two elements based on the specified
     * configuration.
     *
     * @param rep_a representation of the first element
     * @param rep_b representation of the second element
     * @param engine the engine used to perform semantic measures computation
     * @param operators the operators on which rely the abstract measure
     * @param conf the configuration
     * @return the similarity score
     * @throws SLIB_Exception
     */
    public double compute(GraphRepresentation rep_a, GraphRepresentation rep_b, SM_Engine engine, RepresentationOperators operators, SMconf conf) throws SLIB_Exception;
}
