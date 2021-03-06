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
package slib.tools.module;

import java.util.Map;
import org.apache.commons.cli.Option;

/**
 * Abstract class defining the default constants of a tool command handler.
 *
 * @author Sebastien Harispe
 *
 */
public abstract class ToolCmdHandlerCst {

    String appCmdName;
    boolean debugMode;
    Map<Option, Integer> optionsOrder;

    /**
     *
     * @param appCmdName
     * @param debugMode
     * @param optionsOrder
     */
    public ToolCmdHandlerCst(String appCmdName, boolean debugMode, Map<Option, Integer> optionsOrder) {
        this.appCmdName = appCmdName;
        this.debugMode = debugMode;
        this.optionsOrder = optionsOrder;
    }

    /**
     *
     * @return the name of the command line
     */
    public String getAppCmdName() {
        return appCmdName;
    }

    /**
     *
     * @return true if the application is in debug mode
     */
    public boolean getDebugMode() {
        return debugMode;
    }

    /**
     *
     * @return the option order
     */
    public Map<Option, Integer> getOptionOrder() {
        return optionsOrder;
    }
}
