package slib.sglib.io.loader.rdf;

import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.rio.RDFHandler;
import org.openrdf.rio.RDFHandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import slib.sglib.model.graph.G;
import slib.sglib.model.impl.repo.URIFactoryMemory;
import slib.sglib.model.repo.URIFactory;

/**
 *
 * @author seb
 */
public class SlibRdfHandler implements RDFHandler {

    G g;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    int count = 0;
    int countSkipped = 0;
    URIFactory factory;

    /**
     *
     * @param g
     */
    public SlibRdfHandler(G g) {
        this.g = g;
        factory = URIFactoryMemory.getSingleton();
    }

    @Override
    public void startRDF() throws RDFHandlerException {

        logger.info("Start Process");
        count = 0;
    }

    @Override
    public void endRDF() throws RDFHandlerException {

        logger.info("Ending Process " + count + " statements loaded ");
        logger.info("vertices: " + g.getV().size());
        logger.info("edges   : " + g.getE().size());
        logger.info("Skipped (statement involving non URI ressources) : " + countSkipped);
    }

    @Override
    // TODO
    public void handleNamespace(String prefix, String uri) throws RDFHandlerException {
    }

    @Override
    public void handleStatement(Statement st) throws RDFHandlerException {
        
        Value s = st.getSubject();
        Value o = st.getObject();
        
//        logger.debug(st.toString());
        

        if (s instanceof URI && o instanceof URI) {
            g.addE((URI) s,st.getPredicate(),(URI) o);
            count++;
        }
        else{
            countSkipped++;
        }
        if(count % 100000 == 0){
            logger.info(count+" statements already loaded");
            logger.info("Number of vertices: "+g.getV().size());
            logger.info("Number of edges   : "+g.getE().size());
        }
    }

    @Override
    public void handleComment(String comment) throws RDFHandlerException {}
}
