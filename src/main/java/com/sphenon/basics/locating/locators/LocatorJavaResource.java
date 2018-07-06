package com.sphenon.basics.locating.locators;

/****************************************************************************
  Copyright 2001-2018 Sphenon GmbH

  Licensed under the Apache License, Version 2.0 (the "License"); you may not
  use this file except in compliance with the License. You may obtain a copy
  of the License at http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
  WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
  License for the specific language governing permissions and limitations
  under the License.
*****************************************************************************/

import com.sphenon.basics.context.*;
import com.sphenon.basics.message.*;
import com.sphenon.basics.exception.*;
import com.sphenon.basics.notification.*;
import com.sphenon.basics.customary.*;
import com.sphenon.basics.configuration.*;
import com.sphenon.basics.expression.*;
import com.sphenon.basics.graph.*;

import com.sphenon.basics.locating.*;
import com.sphenon.basics.locating.returncodes.*;

import java.util.Vector;

public class LocatorJavaResource extends Locator {

    public LocatorJavaResource (CallContext context, String text_locator_value, Locator sub_locator, String locator_class_parameter_string) {
        super(context, text_locator_value, sub_locator, locator_class_parameter_string);
    }


    /* Parser States -------------------------------------------------------------------- */

    static protected LocatorParserState[] locator_parser_state;
        
    protected LocatorParserState[] getParserStates(CallContext context) {
        if (locator_parser_state == null) {
            locator_parser_state = new LocatorParserState[] {
                new LocatorParserState(context, "name", "name::String:0", false, true, JavaResource.class)
            };
        }
        return locator_parser_state;
    }

    /* Base Acceptors ------------------------------------------------------------------- */

    static protected Vector<LocatorBaseAcceptor> locator_base_acceptors;

    static protected Vector<LocatorBaseAcceptor> initBaseAcceptors(CallContext context) {
        if (locator_base_acceptors == null) {
            locator_base_acceptors = new Vector<LocatorBaseAcceptor>();
            // [MAYBE] we accept JavaResources as Base some time (see also below)
            // locator_base_acceptors.add(new LocatorBaseAcceptor(context, JavaResource.class));
        }
        return locator_base_acceptors;
    }

    protected Vector<LocatorBaseAcceptor> getBaseAcceptors(CallContext context) {
        return initBaseAcceptors(context);
    }

    static public void addBaseAcceptor(CallContext context, LocatorBaseAcceptor base_acceptor) {
        initBaseAcceptors(context).add(base_acceptor);
    }
    
    /* ---------------------------------------------------------------------------------- */

    public String getTargetVariableName(CallContext context) {
        return "java_resource";
    }

    protected Object retrieveLocalTarget(CallContext context) throws InvalidLocator {
        // [MAYBE] we accept JavaResources as Base some time (see also above)
        // Object base = lookupBaseObject(context, false);

        JavaResource java_resource = new JavaResource(context, this.getTextLocatorValue(context));

        return java_resource;
    }

    public String getResolvedTextLocatorValue (CallContext context) throws InvalidLocator {
        return this.text_locator_value;
    }
    
    public String doGetTextLocator (CallContext context, Locator relative_to, String result_locator_class) {

        if (    relative_to.getLocatorClassId(context).equals("Space")
             && (    (    result_locator_class != null
                       && "current_process/current_class_loader".equals(relative_to.getTextLocatorValue(context))
                       && "JavaResource".equals(result_locator_class)
                     )
                  || (    result_locator_class == null
                       && "current_process/locator_factory".equals(relative_to.getTextLocatorValue(context))
                     )
                )
           ) {
            String result_locator = this.text_locator_value;

            if (this.sub_locator != null && this.sub_locator instanceof LocatorPath) {
                result_locator += concatenate(context, result_locator, this.sub_locator.getTextLocatorValue(context));
            }

            return (result_locator_class == null ? "ctn://JavaResource/" : "") + result_locator;
        }

        return super.doGetTextLocator(context, relative_to, result_locator_class);
    }
}
