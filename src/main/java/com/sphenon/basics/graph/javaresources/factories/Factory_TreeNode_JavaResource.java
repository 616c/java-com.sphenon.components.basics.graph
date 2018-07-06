package com.sphenon.basics.graph.javaresources.factories;

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
import com.sphenon.basics.context.classes.*;
import com.sphenon.basics.exception.*;
import com.sphenon.basics.message.*;
import com.sphenon.basics.notification.*;
import com.sphenon.basics.customary.*;
import com.sphenon.basics.configuration.*;
import com.sphenon.basics.factory.*;
import com.sphenon.basics.expression.*;
import com.sphenon.basics.validation.returncodes.*;

import com.sphenon.basics.graph.*;
import com.sphenon.basics.graph.tplinst.*;
import com.sphenon.basics.graph.javaresources.*;

import java.io.*;

public class Factory_TreeNode_JavaResource implements Factory {

    /* -------------- extensible factory instantiation --------------------------------------------------------------------------------------- */
    static protected FactoryInstantiator<Factory_TreeNode_JavaResource> factory_instantiator;
    static {
        CallContext context = RootContext.getInitialisationContext();
        factory_instantiator = new FactoryInstantiator(context, Factory_TreeNode_JavaResource.class) { protected Factory_TreeNode_JavaResource createDefault(CallContext context) { return new Factory_TreeNode_JavaResource(context); } };
    };
    /* --------------------------------------------------------------------------------------------------------------------------------------- */
    static public Factory_TreeNode_JavaResource newInstance (CallContext context) {
        return factory_instantiator.newInstance(context);
    }
    /* --------------------------------------------------------------------------------------------------------------------------------------- */

    protected Factory_TreeNode_JavaResource (CallContext context) {
    }

    static public TreeNode_JavaResource construct (CallContext context, String package_path) throws ValidationFailure {
        Factory_TreeNode_JavaResource factory = newInstance(context);
        factory.setPackagePath(context, package_path);
        factory.setNodeType(context, factory.defaultNodeType(context));
        return factory.create(context);
    }

    static public TreeNode_JavaResource construct (CallContext context, String package_path, NodeType node_type) throws ValidationFailure {
        Factory_TreeNode_JavaResource factory = newInstance(context);
        factory.setPackagePath(context, package_path);
        factory.setNodeType(context, node_type);
        return factory.create(context);
    }

    static public TreeNode_JavaResource tryConstruct (CallContext context, String package_path) {
        Factory_TreeNode_JavaResource factory = newInstance(context);
        factory.setPackagePath(context, package_path);
        factory.setNodeType(context, factory.defaultNodeType(context));
        return factory.tryCreate(context);
    }

    static public TreeNode_JavaResource tryConstruct (CallContext context, String package_path, NodeType node_type) {
        Factory_TreeNode_JavaResource factory = newInstance(context);
        factory.setPackagePath(context, package_path);
        factory.setNodeType(context, node_type);
        return factory.tryCreate(context);
    }

    static public TreeNode_JavaResource construct (CallContext context, JavaResource java_resource) throws ValidationFailure {
        return construct(context, java_resource.getName(context));
    }

    static public TreeNode_JavaResource construct (CallContext context, JavaResource java_resource, NodeType node_type) throws ValidationFailure {
        return construct(context, java_resource.getName(context), node_type);
    }

    static public TreeNode_JavaResource tryConstruct (CallContext context, JavaResource java_resource) {
        return tryConstruct(context, java_resource.getName(context));
    }

    static public TreeNode_JavaResource tryConstruct (CallContext context, JavaResource java_resource, NodeType node_type) {
        return tryConstruct(context, java_resource.getName(context), node_type);
    }

    public TreeNode_JavaResource create (CallContext context) throws ValidationFailure {
        this.validate(context);
        TreeNode_JavaResource tn = this.tryCreate(context);
        if (tn == null) {
            ValidationFailure.createAndThrow(context, "No such tree node '%(path)'", "path", this.package_path);
        }
        return tn;
    }

    static protected TreeNode_JavaResource root;

    static public void clearAllEntriesOfClassLoader(CallContext context, String loader_id) {
        if (root != null) { root.clearEntriesOfClassLoader(context, loader_id); }
    }

    public TreeNode_JavaResource tryCreate (CallContext context) {
        if (this.parts == null) {
            return null;
        }

        if (this.parts[0] != null && this.parts[0].length() != 0) {
            // case 1: the path looked like this:
            //         / xxx/yyy/zzz... / aaa
            // parts[0] contains: xxx/yyy/zzz
            // parts[1] contains: aaa
            // parts[2] contains: 

            TreeNode_JavaResource parent = tryConstruct(context, "/" + parts[0]);
            return parent == null ? null : parent.tryGetChild(context, parts[1]);

        } else if (this.parts[2] != null && this.parts[2].length() != 0) {
            // case 2: the path looked like this:
            //         / aaa
            // parts[0] contains: 
            // parts[1] contains: 
            // parts[2] contains: aaa
        
            TreeNode_JavaResource parent = tryConstruct(context, "/");
            return parent == null ? null : parent.tryGetChild(context, parts[2]);

        } else {
            // case 3: the path looked like this:
            //         /
            // parts[0] contains: 
            // parts[1] contains: 
            // parts[2] contains: 

//             if (root == null) { System.err.println("Creating root"); }
            return (root == null ? (root = TreeNode_JavaResource.create(context, null, "", -1)) : root);
        }
    }

    public Object createObject    (CallContext context) throws ValidationFailure {
        return create(context);
    }

    public void   reset           (CallContext context) {
    }

    /* --------------------------------------------------------------------------------------------------------------------------------------- */

    protected String package_path;
    protected String[] parts;
    static protected RegularExpression pathre  = new RegularExpression("^(?:/(?:(?:(.+)/([^/]+))|([^/]*))(?:/*))?$");

    public String getPackagePath (CallContext context) {
        return this.package_path;
    }

    public void setPackagePath (CallContext context, String package_path) {
        this.package_path = package_path;
        this.parts = pathre.tryGetMatches(context, this.package_path);
    }

    public void validatePackagePath (CallContext context) throws ValidationFailure {
        if (this.parts == null) {
            ValidationFailure.createAndThrow(context, "Invalid java resource path syntax: '%(path)'", "path", this.package_path);
        }
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

    public void validate (CallContext context) throws ValidationFailure {
        this.validatePackagePath(context);
    }

    /* --------------------------------------------------------------------------------------------------------------------------------------- */

    public void confirmAttributes (CallContext context) {
    }

    public void validateFinally   (CallContext context) throws ValidationFailure {
    }
}
