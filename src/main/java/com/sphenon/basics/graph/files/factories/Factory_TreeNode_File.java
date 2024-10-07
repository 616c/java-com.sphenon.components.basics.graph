package com.sphenon.basics.graph.files.factories;

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
import com.sphenon.basics.validation.returncodes.*;

import com.sphenon.basics.graph.*;
import com.sphenon.basics.graph.tplinst.*;
import com.sphenon.basics.graph.files.*;

import java.io.*;
import java.nio.file.*;

public class Factory_TreeNode_File implements Factory {

    /* -------------- extensible factory instantiation --------------------------------------------------------------------------------------- */
    static protected FactoryInstantiator<Factory_TreeNode_File> factory_instantiator;
    static {
      CallContext context = RootContext.getInitialisationContext();
      factory_instantiator = new FactoryInstantiator(context, Factory_TreeNode_File.class) { protected Factory_TreeNode_File createDefault(CallContext context) { return new Factory_TreeNode_File(context); } };
    };
    /* --------------------------------------------------------------------------------------------------------------------------------------- */
    static public Factory_TreeNode_File newInstance (CallContext context) {
        return factory_instantiator.newInstance(context);
    }
    /* --------------------------------------------------------------------------------------------------------------------------------------- */

    protected Factory_TreeNode_File (CallContext context) {
    }

    static public TreeNode_File construct (CallContext context, File file) throws ValidationFailure {
        Factory_TreeNode_File factory = newInstance(context);
        factory.setFile(context, file);
        factory.setParent(context, factory.defaultParent(context));
        factory.setNodeType(context, factory.defaultNodeType(context));
        return factory.create(context);
    }

    static public TreeNode_File construct (CallContext context, File file, NodeType node_type) throws ValidationFailure {
        Factory_TreeNode_File factory = newInstance(context);
        factory.setFile(context, file);
        factory.setParent(context, factory.defaultParent(context));
        factory.setNodeType(context, node_type);
        return factory.create(context);
    }

    static public TreeNode_File construct (CallContext context, File file, NodeType node_type, boolean allow_new) throws ValidationFailure {
        Factory_TreeNode_File factory = newInstance(context);
        factory.setFile(context, file);
        factory.setParent(context, factory.defaultParent(context));
        factory.setNodeType(context, node_type);
        factory.setAllowNew(context, allow_new);
        return factory.create(context);
    }

    static public TreeNode_File tryConstruct (CallContext context, File file) {
        Factory_TreeNode_File factory = newInstance(context);
        factory.setFile(context, file);
        factory.setParent(context, factory.defaultParent(context));
        factory.setNodeType(context, factory.defaultNodeType(context));
        return factory.tryCreate(context);
    }

    static public TreeNode_File tryConstruct (CallContext context, File file, NodeType node_type) {
        Factory_TreeNode_File factory = newInstance(context);
        factory.setFile(context, file);
        factory.setParent(context, factory.defaultParent(context));
        factory.setNodeType(context, node_type);
        return factory.tryCreate(context);
    }

    static public TreeNode_File tryConstruct (CallContext context, File file, NodeType node_type, boolean allow_new) {
        Factory_TreeNode_File factory = newInstance(context);
        factory.setFile(context, file);
        factory.setParent(context, factory.defaultParent(context));
        factory.setNodeType(context, node_type);
        factory.setAllowNew(context, allow_new);
        return factory.tryCreate(context);
    }

    static public TreeNode_File construct (CallContext context, File file, TreeNode parent) throws ValidationFailure {
        Factory_TreeNode_File factory = newInstance(context);
        factory.setFile(context, file);
        factory.setParent(context, parent);
        factory.setNodeType(context, factory.defaultNodeType(context));
        return factory.create(context);
    }

    static public TreeNode_File construct (CallContext context, File file, TreeNode parent, NodeType node_type) throws ValidationFailure {
        Factory_TreeNode_File factory = newInstance(context);
        factory.setFile(context, file);
        factory.setParent(context, parent);
        factory.setNodeType(context, node_type);
        return factory.create(context);
    }

    static public TreeNode_File construct (CallContext context, File file, TreeNode parent, NodeType node_type, boolean allow_new) throws ValidationFailure {
        Factory_TreeNode_File factory = newInstance(context);
        factory.setFile(context, file);
        factory.setParent(context, parent);
        factory.setNodeType(context, node_type);
        factory.setAllowNew(context, allow_new);
        return factory.create(context);
    }

    static public TreeNode_File tryConstruct (CallContext context, File file, TreeNode parent) {
        Factory_TreeNode_File factory = newInstance(context);
        factory.setFile(context, file);
        factory.setParent(context, parent);
        factory.setNodeType(context, factory.defaultNodeType(context));
        return factory.tryCreate(context);
    }

    static public TreeNode_File tryConstruct (CallContext context, File file, TreeNode parent, NodeType node_type) {
        Factory_TreeNode_File factory = newInstance(context);
        factory.setFile(context, file);
        factory.setParent(context, parent);
        factory.setNodeType(context, node_type);
        return factory.tryCreate(context);
    }

    static public TreeNode_File tryConstruct (CallContext context, File file, TreeNode parent, NodeType node_type, boolean allow_new) {
        Factory_TreeNode_File factory = newInstance(context);
        factory.setFile(context, file);
        factory.setParent(context, parent);
        factory.setNodeType(context, node_type);
        factory.setAllowNew(context, allow_new);
        return factory.tryCreate(context);
    }

    public TreeNode_File create (CallContext context) throws ValidationFailure {
        TreeNode_File tn = this.tryCreate(context);
        if (tn == null) {
            ValidationFailure.createAndThrow(context, "No such tree node (file) '%(path)'", "path", this.file.getPath());
        }
        return tn;
    }

    public TreeNode_File tryCreate (CallContext context) {
        if (Files.isSymbolicLink(this.file.toPath())) {
            File target_file = null;
            try {
                target_file = Files.readSymbolicLink(this.file.toPath()).toFile();
            } catch (Throwable t) {
            }
            if (    (this.file.getName().startsWith("##FOLDER##") == true)
                 || (target_file != null && target_file.isDirectory())
                 || ((target_file == null || target_file.exists() == false) && node_type == NodeType.NODE)
               ) {
                return TreeNode_File.create(context, file, parent, this.allow_new);
            }
            if (    (this.file.getName().startsWith("##FILE##") == true)
                 || (target_file != null && target_file.isFile())
                 || ((target_file == null || target_file.exists() == false) && node_type == NodeType.LEAF)
               ) {
                return TreeLeaf_File.create(context, file, parent, this.allow_new);
            }
            CustomaryContext.create((Context)context).throwPreConditionViolation(context, "Target file '%(targetfile)' pointed to by symbolic link '%(file)' is neither a file nor a directory and symbolic link name is not explicitly marked with '##FILE##' or '##FOLDER##' or does not match expected node type '%(nodetype)'", "targetfile", target_file.getPath(), "file", this.file.getPath(), "nodetype", node_type);
            throw (ExceptionPreConditionViolation) null; // compiler insists
        }
        if (this.file.exists() == false && node_type == NodeType.ANY) {
            return null;
        }
        if (this.file.isDirectory() || (this.file.exists() == false && node_type == NodeType.NODE)) {
            return TreeNode_File.create(context, file, parent, this.allow_new);
        }
        if (this.file.isFile() || (this.file.exists() == false && node_type == NodeType.LEAF)) {
            return TreeLeaf_File.create(context, file, parent, this.allow_new);
        }
        CustomaryContext.create((Context)context).throwPreConditionViolation(context, "File '%(file)' is neither a file nor a directory or does not match expected node type '%(nodetype)'", "file", this.file.getPath(), "nodetype", node_type);
        throw (ExceptionPreConditionViolation) null; // compiler insists
    }

    public Object createObject    (CallContext context) throws ValidationFailure {
        return create(context);
    }

    public void   reset           (CallContext context) {
    }

    /* --------------------------------------------------------------------------------------------------------------------------------------- */

    protected File file;

    public File getFile (CallContext context) {
        return this.file;
    }

    public void setFile (CallContext context, File file) {
        this.file = file;
    }

    protected TreeNode parent;

    public TreeNode getParent (CallContext context) {
        return this.parent;
    }

    public TreeNode defaultParent (CallContext context) {
        return null;
    }

    public void setParent (CallContext context, TreeNode parent) {
        this.parent = parent;
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

    public void   validate        (CallContext context) throws ValidationFailure {
    }

    /* --------------------------------------------------------------------------------------------------------------------------------------- */

    public void confirmAttributes (CallContext context) {
    }

    public void validateFinally   (CallContext context) throws ValidationFailure {
    }
}
