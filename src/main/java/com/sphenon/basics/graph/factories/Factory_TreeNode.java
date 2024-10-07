package com.sphenon.basics.graph.factories;

/****************************************************************************
  Copyright 2001-2024 Sphenon GmbH

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
import com.sphenon.basics.context.classes.*;
import com.sphenon.basics.exception.*;
import com.sphenon.basics.message.*;
import com.sphenon.basics.notification.*;
import com.sphenon.basics.customary.*;
import com.sphenon.basics.configuration.*;
import com.sphenon.basics.factory.*;
import com.sphenon.basics.expression.*;
import com.sphenon.basics.locating.*;
import com.sphenon.basics.locating.factories.*;
import com.sphenon.basics.locating.classes.*;
import com.sphenon.basics.locating.returncodes.*;
import com.sphenon.basics.validation.returncodes.*;

import com.sphenon.basics.graph.*;
import com.sphenon.basics.graph.tplinst.*;
import com.sphenon.basics.graph.files.factories.*;
import com.sphenon.basics.graph.javaresources.factories.*;

import java.io.*;

public class Factory_TreeNode implements Factory {

    /* -------------- extensible factory instantiation --------------------------------------------------------------------------------------- */
    static protected FactoryInstantiator<Factory_TreeNode> factory_instantiator;
    static {
      CallContext context = RootContext.getInitialisationContext();
      factory_instantiator = new FactoryInstantiator(context, Factory_TreeNode.class) { protected Factory_TreeNode createDefault(CallContext context) { return new Factory_TreeNode(context); } };
    };
    /* --------------------------------------------------------------------------------------------------------------------------------------- */
    static public Factory_TreeNode newInstance (CallContext context) {
        return factory_instantiator.newInstance(context);
    }
    /* --------------------------------------------------------------------------------------------------------------------------------------- */

    protected Factory_TreeNode (CallContext context) {
    }

    static public TreeNode construct (CallContext context, Location location) throws ValidationFailure {
        Factory_TreeNode factory = newInstance(context);
        factory.setLocation(context, location);
        factory.setNodeType(context, factory.defaultNodeType(context));
        factory.setAllowNew(context, true);
        return factory.create(context);
    }

    static public TreeNode construct (CallContext context, Location location, NodeType node_type) throws ValidationFailure {
        Factory_TreeNode factory = newInstance(context);
        factory.setLocation(context, location);
        factory.setNodeType(context, node_type);
        factory.setAllowNew(context, true);
        return factory.create(context);
    }

    static public TreeNode construct (CallContext context, Location location, NodeType node_type, boolean allow_new) throws ValidationFailure {
        Factory_TreeNode factory = newInstance(context);
        factory.setLocation(context, location);
        factory.setNodeType(context, node_type);
        factory.setAllowNew(context, allow_new);
        return factory.create(context);
    }

    static public TreeNode tryConstruct (CallContext context, Location location) {
        Factory_TreeNode factory = newInstance(context);
        factory.setLocation(context, location);
        factory.setNodeType(context, factory.defaultNodeType(context));
        factory.setAllowNew(context, true);
        return factory.tryCreate(context);
    }

    static public TreeNode tryConstruct (CallContext context, Location location, NodeType node_type) {
        Factory_TreeNode factory = newInstance(context);
        factory.setLocation(context, location);
        factory.setNodeType(context, node_type);
        factory.setAllowNew(context, true);
        return factory.tryCreate(context);
    }

    static public TreeNode tryConstruct (CallContext context, Location location, NodeType node_type, boolean allow_new) {
        Factory_TreeNode factory = newInstance(context);
        factory.setLocation(context, location);
        factory.setNodeType(context, node_type);
        factory.setAllowNew(context, allow_new);
        return factory.tryCreate(context);
    }

    static public TreeNode construct (CallContext context, Locator locator) throws ValidationFailure {
        Factory_TreeNode factory = newInstance(context);
        factory.setLocation(context, Factory_Location.construct(context, locator));
        factory.setNodeType(context, factory.defaultNodeType(context));
        factory.setAllowNew(context, true);
        return factory.create(context);
    }

    static public TreeNode construct (CallContext context, Locator locator, NodeType node_type) throws ValidationFailure {
        Factory_TreeNode factory = newInstance(context);
        factory.setLocation(context, Factory_Location.construct(context, locator));
        factory.setNodeType(context, node_type);
        factory.setAllowNew(context, true);
        return factory.create(context);
    }

    static public TreeNode construct (CallContext context, Locator locator, NodeType node_type, boolean allow_new) throws ValidationFailure {
        Factory_TreeNode factory = newInstance(context);
        factory.setLocation(context, Factory_Location.construct(context, locator));
        factory.setNodeType(context, node_type);
        factory.setAllowNew(context, allow_new);
        return factory.create(context);
    }

    static public TreeNode tryConstruct (CallContext context, Locator locator) {
        Factory_TreeNode factory = newInstance(context);
        factory.setLocation(context, Factory_Location.construct(context, locator));
        factory.setNodeType(context, factory.defaultNodeType(context));
        factory.setAllowNew(context, true);
        return factory.tryCreate(context);
    }

    static public TreeNode tryConstruct (CallContext context, Locator locator, NodeType node_type) {
        Factory_TreeNode factory = newInstance(context);
        factory.setLocation(context, Factory_Location.construct(context, locator));
        factory.setNodeType(context, node_type);
        factory.setAllowNew(context, true);
        return factory.tryCreate(context);
    }

    static public TreeNode tryConstruct (CallContext context, Locator locator, NodeType node_type, boolean allow_new) {
        Factory_TreeNode factory = newInstance(context);
        factory.setLocation(context, Factory_Location.construct(context, locator));
        factory.setNodeType(context, node_type);
        factory.setAllowNew(context, allow_new);
        return factory.tryCreate(context);
    }

    static public TreeNode construct (CallContext context, String locator) throws ValidationFailure {
        Factory_TreeNode factory = newInstance(context);
        factory.setLocation(context, Factory_Location.construct(context, Factory_Locator.construct(context, locator)));
        factory.setNodeType(context, factory.defaultNodeType(context));
        factory.setAllowNew(context, true);
        return factory.create(context);
    }

    static public TreeNode construct (CallContext context, String locator, NodeType node_type) throws ValidationFailure {
        Factory_TreeNode factory = newInstance(context);
        factory.setLocation(context, Factory_Location.construct(context, Factory_Locator.construct(context, locator)));
        factory.setNodeType(context, node_type);
        factory.setAllowNew(context, true);
        return factory.create(context);
    }

    static public TreeNode construct (CallContext context, String locator, NodeType node_type, boolean allow_new) throws ValidationFailure {
        Factory_TreeNode factory = newInstance(context);
        factory.setLocation(context, Factory_Location.construct(context, Factory_Locator.construct(context, locator)));
        factory.setNodeType(context, node_type);
        factory.setAllowNew(context, allow_new);
        return factory.create(context);
    }

    static public TreeNode tryConstruct (CallContext context, String locator) {
        Factory_TreeNode factory = newInstance(context);
        factory.setLocation(context, Factory_Location.construct(context, Factory_Locator.tryConstruct(context, locator)));
        factory.setNodeType(context, factory.defaultNodeType(context));
        factory.setAllowNew(context, true);
        return factory.tryCreate(context);
    }

    static public TreeNode tryConstruct (CallContext context, String locator, NodeType node_type) {
        Factory_TreeNode factory = newInstance(context);
        factory.setLocation(context, Factory_Location.construct(context, Factory_Locator.tryConstruct(context, locator)));
        factory.setNodeType(context, node_type);
        factory.setAllowNew(context, true);
        return factory.tryCreate(context);
    }

    static public TreeNode tryConstruct (CallContext context, String locator, NodeType node_type, boolean allow_new) {
        Factory_TreeNode factory = newInstance(context);
        factory.setLocation(context, Factory_Location.construct(context, Factory_Locator.tryConstruct(context, locator)));
        factory.setNodeType(context, node_type);
        factory.setAllowNew(context, allow_new);
        return factory.tryCreate(context);
    }

    public TreeNode create (CallContext context) throws ValidationFailure {
        return this.doCreate(context, true);
    }

    public TreeNode tryCreate (CallContext context) {
        try {
            return this.doCreate(context, false);
        } catch (ValidationFailure vf) {
            // should not happen
            return null;
        }
    }

    static protected Locator cpcll;
    static protected Locator lhfsl;

    // [ToDo]
    // in Factory_Agregate cleanup locator resolution, don't do it
    // there, but here

    protected TreeNode doCreate (CallContext context, boolean throw_exception) throws ValidationFailure {
        if (cpcll == null) { cpcll = Factory_Locator.construct(context, "ctn://Space/current_process/current_class_loader"); }
        if (lhfsl == null) { lhfsl = Factory_Locator.construct(context, "ctn://Space/local_host/file_system"); }

        String jtlv = this.location.tryGetTextLocatorValue(context, cpcll, "JavaResource");
        if (jtlv != null) {
            if (throw_exception) {
                return Factory_TreeNode_JavaResource.construct(context, jtlv, this.node_type);
            } else {
                return Factory_TreeNode_JavaResource.tryConstruct(context, jtlv, this.node_type);
            }
        }
        String ftlv = this.location.tryGetTextLocatorValue(context, lhfsl, "File");
        if (ftlv != null) {
            if (throw_exception) {
                return Factory_TreeNode_File.construct(context, new File(ftlv), this.node_type, this.allow_new);
            } else {
                return Factory_TreeNode_File.tryConstruct(context, new File(ftlv), this.node_type, this.allow_new);
            }
        }

        if (this.location instanceof Class_Location) {
            Object o;

            Locator locator = ((Class_Location) this.location).getLocator(context);

            o = FactoryTreeNodeByLocator.get(context).tryCreate(context, locator);

            if (o != null) { return (TreeNode) o; }

            try {
                o = locator.retrieveTarget(context);
            } catch (InvalidLocator il) {
                ValidationFailure.createAndThrow(context, il, "Cannot resolve locator");
                throw (ValidationFailure) null;
            }

            if (o instanceof File) {
                if (throw_exception) {
                    return Factory_TreeNode_File.construct(context, (File) o, this.node_type, this.allow_new);
                } else {
                    return Factory_TreeNode_File.tryConstruct(context, (File) o, this.node_type, this.allow_new);
                }
            }
            if (o instanceof JavaResource) {
                if (throw_exception) {
                    return Factory_TreeNode_JavaResource.construct(context, (JavaResource) o, this.node_type);
                } else {
                    return Factory_TreeNode_JavaResource.tryConstruct(context, (JavaResource) o, this.node_type);
                }
            }
        }

        CustomaryContext.create((Context)context).throwPreConditionViolation(context, "Location '%(location)' is not valid (expected 'file:...' or 'ctn://File or JavaResource...' or plain path or something that evaluates to a File or JavaResource or any other kind of graph node)", "location", this.location);
        throw (ExceptionPreConditionViolation) null; // compiler insists
    }

    public Object createObject    (CallContext context) throws ValidationFailure {
        return create(context);
    }

    public void   reset           (CallContext context) {
    }

    /* --------------------------------------------------------------------------------------------------------------------------------------- */

    protected Location location;

    public Location getLocation (CallContext context) {
        return this.location;
    }

    public void setLocation (CallContext context, Location location) {
        this.location = location;
    }

    public void   validate        (CallContext context) throws ValidationFailure {
    }

    protected NodeType node_type;

    public NodeType getNodeType (CallContext context) {
        return this.node_type;
    }

    public NodeType defaultNodeType (CallContext context) {
        return NodeType.ANY;
    }

    public void setNodeType (CallContext context, NodeType node_type) {
        this.node_type = node_type;
    }

    protected boolean allow_new;

    public boolean getAllowNew (CallContext context) {
        return this.allow_new;
    }

    public boolean defaultAllowNew (CallContext context) {
        return true;
    }

    public void setAllowNew (CallContext context, boolean allow_new) {
        this.allow_new = allow_new;
    }

    /* --------------------------------------------------------------------------------------------------------------------------------------- */

    public void confirmAttributes (CallContext context) {
    }

    public void validateFinally   (CallContext context) throws ValidationFailure {
    }
}
