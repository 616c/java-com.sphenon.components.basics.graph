package com.sphenon.basics.graph;

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
import com.sphenon.basics.message.*;
import com.sphenon.basics.notification.*;
import com.sphenon.basics.customary.*;
import com.sphenon.basics.expression.*;

import com.sphenon.basics.graph.tplinst.*;

public class NodeFilterRegExp implements NodeFilter {

    public NodeFilterRegExp (CallContext context) {
    }

    public NodeFilterRegExp (CallContext context, String node_include_regexp, String node_exclude_regexp, String leaf_include_regexp, String leaf_exclude_regexp, String path_include_regexp, String path_exclude_regexp) {

        this.setNodeIncludeRegExp (context, node_include_regexp);
        this.setNodeExcludeRegExp (context, node_exclude_regexp);
        this.setLeafIncludeRegExp (context, leaf_include_regexp);
        this.setLeafExcludeRegExp (context, leaf_exclude_regexp);
        this.setPathIncludeRegExp (context, path_include_regexp);
        this.setPathExcludeRegExp (context, path_exclude_regexp);
    }

    protected String            node_include_regexp;
    protected RegularExpression node_include_re;

    public String getNodeIncludeRegExp (CallContext context) {
        return this.node_include_regexp;
    }

    public void setNodeIncludeRegExp (CallContext context, String node_include_regexp) {
        this.node_include_regexp = node_include_regexp;
        this.node_include_re     = this.node_include_regexp == null ? null : new RegularExpression(context, this.node_include_regexp);
    }

    public String defaultNodeIncludeRegExp (CallContext context) {
        return null;
    }

    protected String            node_exclude_regexp;
    protected RegularExpression node_exclude_re;

    public String getNodeExcludeRegExp (CallContext context) {
        return this.node_exclude_regexp;
    }

    public void setNodeExcludeRegExp (CallContext context, String node_exclude_regexp) {
        this.node_exclude_regexp = node_exclude_regexp;
        this.node_exclude_re     = this.node_exclude_regexp == null ? null : new RegularExpression(context, this.node_exclude_regexp);
    }

    public String defaultNodeExcludeRegExp (CallContext context) {
        return null;
    }

    protected String            leaf_include_regexp;
    protected RegularExpression leaf_include_re;

    public String getLeafIncludeRegExp (CallContext context) {
        return this.leaf_include_regexp;
    }

    public void setLeafIncludeRegExp (CallContext context, String leaf_include_regexp) {
        this.leaf_include_regexp = leaf_include_regexp;
        this.leaf_include_re     = this.leaf_include_regexp == null ? null : new RegularExpression(context, this.leaf_include_regexp);
    }

    public String defaultLeafIncludeRegExp (CallContext context) {
        return null;
    }

    protected String            leaf_exclude_regexp;
    protected RegularExpression leaf_exclude_re;

    public String getLeafExcludeRegExp (CallContext context) {
        return this.leaf_exclude_regexp;
    }

    public void setLeafExcludeRegExp (CallContext context, String leaf_exclude_regexp) {
        this.leaf_exclude_regexp = leaf_exclude_regexp;
        this.leaf_exclude_re     = this.leaf_exclude_regexp == null ? null : new RegularExpression(context, this.leaf_exclude_regexp);
    }

    public String defaultLeafExcludeRegExp (CallContext context) {
        return null;
    }

    protected String            path_include_regexp;
    protected RegularExpression path_include_re;

    public String getPathIncludeRegExp (CallContext context) {
        return this.path_include_regexp;
    }

    public void setPathIncludeRegExp (CallContext context, String path_include_regexp) {
        this.path_include_regexp = path_include_regexp;
        this.path_include_re     = this.path_include_regexp == null ? null : new RegularExpression(context, this.path_include_regexp);
    }

    public String defaultPathIncludeRegExp (CallContext context) {
        return null;
    }

    protected String            path_exclude_regexp;
    protected RegularExpression path_exclude_re;

    public String getPathExcludeRegExp (CallContext context) {
        return this.path_exclude_regexp;
    }

    public void setPathExcludeRegExp (CallContext context, String path_exclude_regexp) {
        this.path_exclude_regexp = path_exclude_regexp;
        this.path_exclude_re     = this.path_exclude_regexp == null ? null : new RegularExpression(context, this.path_exclude_regexp);
    }

    public String defaultPathExcludeRegExp (CallContext context) {
        return null;
    }

    protected String            node_path_include_regexp;
    protected RegularExpression node_path_include_re;

    public String getNodePathIncludeRegExp (CallContext context) {
        return this.node_path_include_regexp;
    }

    public void setNodePathIncludeRegExp (CallContext context, String node_path_include_regexp) {
        this.node_path_include_regexp = node_path_include_regexp;
        this.node_path_include_re     = this.node_path_include_regexp == null ? null : new RegularExpression(context, this.node_path_include_regexp);
    }

    public String defaultNodePathIncludeRegExp (CallContext context) {
        return null;
    }

    protected String            node_path_exclude_regexp;
    protected RegularExpression node_path_exclude_re;

    public String getNodePathExcludeRegExp (CallContext context) {
        return this.node_path_exclude_regexp;
    }

    public void setNodePathExcludeRegExp (CallContext context, String node_path_exclude_regexp) {
        this.node_path_exclude_regexp = node_path_exclude_regexp;
        this.node_path_exclude_re     = this.node_path_exclude_regexp == null ? null : new RegularExpression(context, this.node_path_exclude_regexp);
    }

    public String defaultNodePathExcludeRegExp (CallContext context) {
        return null;
    }

    protected String            leaf_path_include_regexp;
    protected RegularExpression leaf_path_include_re;

    public String getLeafPathIncludeRegExp (CallContext context) {
        return this.leaf_path_include_regexp;
    }

    public void setLeafPathIncludeRegExp (CallContext context, String leaf_path_include_regexp) {
        this.leaf_path_include_regexp = leaf_path_include_regexp;
        this.leaf_path_include_re     = this.leaf_path_include_regexp == null ? null : new RegularExpression(context, this.leaf_path_include_regexp);
    }

    public String defaultLeafPathIncludeRegExp (CallContext context) {
        return null;
    }

    protected String            leaf_path_exclude_regexp;
    protected RegularExpression leaf_path_exclude_re;

    public String getLeafPathExcludeRegExp (CallContext context) {
        return this.leaf_path_exclude_regexp;
    }

    public void setLeafPathExcludeRegExp (CallContext context, String leaf_path_exclude_regexp) {
        this.leaf_path_exclude_regexp = leaf_path_exclude_regexp;
        this.leaf_path_exclude_re     = this.leaf_path_exclude_regexp == null ? null : new RegularExpression(context, this.leaf_path_exclude_regexp);
    }

    public String defaultLeafPathExcludeRegExp (CallContext context) {
        return null;
    }

    public boolean matches(CallContext context, TreeNode node) {
        String id   = node.getId(context);
        String path = node.getPath(context);
        return (
                    (    this.path_include_regexp == null || this.path_include_regexp.length() == 0 || this.path_include_re == null
                      || this.path_include_re.matches(context, path) == true
                    )
                 && (    this.path_exclude_regexp == null || this.path_exclude_regexp.length() == 0 || this.path_exclude_re == null
                      || this.path_exclude_re.matches(context, path) == false
                    )
                 && (node instanceof TreeLeaf ?
                        (    (    this.leaf_include_regexp == null || this.leaf_include_regexp.length() == 0 || this.leaf_include_re == null
                               || this.leaf_include_re.matches(context, id) == true
                             )
                          && (    this.leaf_exclude_regexp == null || this.leaf_exclude_regexp.length() == 0 || this.leaf_exclude_re == null
                               || this.leaf_exclude_re.matches(context, id) == false
                             )
                        )
                     :  (    (    this.node_include_regexp == null || this.node_include_regexp.length() == 0 || this.node_include_re == null
                               || this.node_include_re.matches(context, id) == true
                             )
                          && (    this.node_exclude_regexp == null || this.node_exclude_regexp.length() == 0 || this.node_exclude_re == null
                               || this.node_exclude_re.matches(context, id) == false
                             )
                        )
                    )
                 && (node instanceof TreeLeaf ?
                        (    (    this.leaf_path_include_regexp == null || this.leaf_path_include_regexp.length() == 0 || this.leaf_path_include_re == null
                               || this.leaf_path_include_re.matches(context, path) == true
                             )
                          && (    this.leaf_path_exclude_regexp == null || this.leaf_path_exclude_regexp.length() == 0 || this.leaf_path_exclude_re == null
                               || this.leaf_path_exclude_re.matches(context, path) == false
                             )
                        )
                     :  (    (    this.node_path_include_regexp == null || this.node_path_include_regexp.length() == 0 || this.node_path_include_re == null
                               || this.node_path_include_re.matches(context, path) == true
                             )
                          && (    this.node_path_exclude_regexp == null || this.node_path_exclude_regexp.length() == 0 || this.node_path_exclude_re == null
                               || this.node_path_exclude_re.matches(context, path) == false
                             )
                        )
                    )
               );
    }
}
