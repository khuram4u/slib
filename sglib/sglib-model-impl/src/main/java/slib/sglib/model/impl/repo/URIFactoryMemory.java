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
package slib.sglib.model.impl.repo;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import org.openrdf.model.URI;
import org.openrdf.sail.memory.model.MemValueFactory;
import slib.sglib.model.repo.URIFactory;
import slib.utils.ex.SLIB_Ex_Critic;

/**
 * This class defines the singleton used as an in memory repository which manage
 * all used URI and storage element i.e. graphs. The main goal of the
 * DataFactory singleton is to avoid URI and NameSpace object duplicates and
 * must therefore be used to create and load URIs.
 *
 * The repository must be in agreement to graph representation i.e. URI loaded
 * in a graph are linked to the corresponding storage element in the Data
 * repository. All change of the graph must be propagated on the Data Repository
 *
 * @author Sebastien Harispe
 *
 */
public class URIFactoryMemory implements URIFactory {

    MemValueFactory internalUriFactory;
    private static URIFactoryMemory repository;
    private Map<String, String> namespacePrefixes2namespaces;
    private Map<String, String> namespaces2namespacePrefixes;
    Pattern colon = Pattern.compile(":");

    /**
     * Access to the in-memory URI Factory.
     *
     * @return the singleton
     */
    public static URIFactoryMemory getSingleton() {

        if (repository == null) {
            repository = new URIFactoryMemory();
        }
        return repository;
    }

    /**
     * Create a {@link URIFactory} which relies on in-memory data structures.
     */
    private URIFactoryMemory() {

        internalUriFactory = new MemValueFactory();
        namespacePrefixes2namespaces = new HashMap<String, String>();
        namespaces2namespacePrefixes = new HashMap<String, String>();
    }

    @Override
    public boolean loadNamespacePrefix(String prefix, String reference) throws SLIB_Ex_Critic {


        if (!namespacePrefixes2namespaces.containsKey(prefix)) {
            namespacePrefixes2namespaces.put(prefix, reference);
            namespaces2namespacePrefixes.put(reference, prefix);
            return true;
        } else if (namespacePrefixes2namespaces.containsKey(prefix) && !namespacePrefixes2namespaces.get(prefix).equals(reference)) {
            throw new SLIB_Ex_Critic("Cannot include namespace prefix " + prefix + " for namespace " + reference + ""
                    + "\n prefix already linked to " + namespacePrefixes2namespaces.get(prefix));
        }
        return false;
    }

    @Override
    public String getNamespace(String ns_prefix) {
        if (ns_prefix == null) {
            return null;
        }
        return namespacePrefixes2namespaces.get(ns_prefix);
    }

    @Override
    public void clear() {
        namespacePrefixes2namespaces.clear();
    }

    @Override
    public URI createURI(String sURI) {
        return internalUriFactory.createURI(sURI);

    }

    @Override
    public URI createURI(String snamespace, String sURI) {
        return internalUriFactory.createURI(snamespace, sURI);
    }

    @Override
    public URI createURI(String sURI, boolean useLoadedPrefix) {
        if (!useLoadedPrefix) {
            return createURI(sURI);
        } else {
            int idx = sURI.indexOf(":");
            if (idx != -1) {
                String prefix = sURI.substring(0, idx);
                if (namespacePrefixes2namespaces.containsKey(prefix)) {
                    return createURI(namespacePrefixes2namespaces.get(prefix), sURI.substring(idx + 1));
                } else {
                    return createURI(sURI);
                }
            } else {
                return createURI(sURI);
            }
        }
    }

    @Override
    public String shortURIasString(URI uri) {
        if (namespaces2namespacePrefixes.containsKey(uri.getNamespace())) {
            return namespaces2namespacePrefixes.get(uri.getNamespace()) + ":" + uri.getLocalName();
        } else {
            return uri.stringValue();
        }
    }
}
