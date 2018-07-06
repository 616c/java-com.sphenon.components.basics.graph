package com.sphenon.basics.graph.test;

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
import com.sphenon.basics.message.*;
import com.sphenon.basics.notification.*;
import com.sphenon.basics.customary.*;
import com.sphenon.basics.locating.*;
import com.sphenon.basics.locating.classes.*;

import com.sphenon.basics.graph.*;
import com.sphenon.basics.graph.tplinst.*;
import com.sphenon.basics.graph.classes.*;
import com.sphenon.basics.graph.factories.*;

import java.io.File;

public class Test {

    public static void dump(CallContext context, TreeNode tn, String indent) {
        if (tn == null) {
            System.err.println(indent + " (null)");
            return;
        }
        String locator = tn.getLocation(context) == null ? "" : ((Class_Location)(tn.getLocation(context))).getLocator(context).getTextLocatorValue(context);
        System.err.printf("%s%-60s%s\n", indent, tn.getId(context), locator);
        for (TreeNode child : new VectorIterable_TreeNode_long_(context, tn.getChilds(context))) {
            dump(context, child, indent + "  ");
        }
    }

    public static void dump(CallContext context, TreeNode tn, String indent, NodeFilter nf) {
        if (tn == null) {
            System.err.println(indent + " (null)");
            return;
        }
        System.err.println(indent + tn.getId(context));
        for (TreeNode child : new VectorIterable_TreeNode_long_(context, tn.getChilds(context, nf))) {
            dump(context, child, indent + "  ", nf);
        }
    }

    public static void main(String[] args) {

        Context context = RootContext.getRootContext();

        System.err.println("A>"+(new Test()).getClass().getClassLoader().getResourceAsStream("/com/sphenon/.index"));
        System.err.println("B>"+(new Test()).getClass().getClassLoader().getResourceAsStream("com/sphenon/.index"));

        TreeNode tn1 = Factory_TreeNode.tryConstruct(context, "/workspace/sphenon/projects/components/basics/graph/v0001/origin/source/java");
        dump(context, tn1, "1: ");

        TreeNode tn2 = Factory_TreeNode.tryConstruct(context, "file:/workspace/sphenon/projects/components/basics/graph/v0001/origin/source/java");
        dump(context, tn2, "2: ");

        TreeNode tn3 = Factory_TreeNode.tryConstruct(context, "ctn://File//workspace/sphenon/projects/components/basics/graph/v0001/origin/source/java");
        dump(context, tn3, "3: ");

        TreeNode tn4 = Factory_TreeNode.tryConstruct(context, "ctn://JavaResource//com/sphenon/");
        dump(context, tn4, "4: ");

        TreeNode tn5 = Factory_TreeNode.tryConstruct(context, "ctn://JavaResource/");
        dump(context, tn5, "5: ");

        NodeFilterRegExp nf = new NodeFilterRegExp(context);
        nf.setLeafIncludeRegExp (context, "^[Tt]est.*$");

        dump(context, tn5, "6: ", nf);

        TreeNode ct1 = tn5.getChildTree(context, nf);
        dump(context, ct1, "7: ");

        NodeFilterRegExp nf2 = new NodeFilterRegExp(context);
        nf2.setLeafIncludeRegExp (context, "^.*Factory.*$");

        TreeNode ct2 = tn5.getChildTree(context, nf2);
        dump(context, ct2, "8: ");

        TreeNode_Union union = new TreeNode_Union(context, null, "", ct1, ct2);
        dump(context, union, "9: ");

        System.err.println("tn3                     " + tn3);
        System.err.println("tn3[2]                  " + tn3.getChilds(context).tryGet(context, 0));
        System.err.println("tn3[2]                  " + tn3.getChilds(context).tryGet(context, 0));
        System.err.println("tn3[2]P                 " + tn3.getChilds(context).tryGet(context, 0).tryGetParent(context));
        System.err.println("tn3[2]P                 " + tn3.getChilds(context).tryGet(context, 0).tryGetParent(context));
        System.err.println("tn5                     " + tn5);
        System.err.println("tn5[0]                  " + tn5.getChilds(context).tryGet(context, 0));
        System.err.println("tn5[0]                  " + tn5.getChilds(context).tryGet(context, 0));
        System.err.println("tn5[0]P                 " + tn5.getChilds(context).tryGet(context, 0).tryGetParent(context));
        System.err.println("tn5[0]P                 " + tn5.getChilds(context).tryGet(context, 0).tryGetParent(context));

        System.err.println("tn3[3][1]               " + tn3.getId(context));
        System.err.println("tn3[3][1]               " + tn3.getChilds(context));
        System.err.println("tn3[3][1]               " + tn3.getChilds(context).tryGet(context, 3));
        System.err.println("tn3[3][1]               " + tn3.getChilds(context).tryGet(context, 3).getId(context));
        System.err.println("tn3[3][1]               " + tn3.getChilds(context).tryGet(context, 3).getChilds(context));
        System.err.println("tn3[3][1]               " + tn3.getChilds(context).tryGet(context, 3).getChilds(context).tryGet(context, 1));
        System.err.println("tn3[3][1]               " + tn3.getChilds(context).tryGet(context, 3).getChilds(context).tryGet(context, 1).getId(context));

        Locator locbase  = ((Class_Location)(tn3.getLocation(context))).getLocator(context);
        Locator locchild = ((Class_Location)(tn3.getChilds(context).tryGet(context, 3).getChilds(context).tryGet(context, 1).getLocation(context))).getLocator(context);
        System.err.println("location base           " + locbase.getTextLocatorValue(context));
        System.err.println("location child          " + locchild.getTextLocatorValue(context));
        System.err.println("location/relative       " + locchild.tryGetTextLocatorValue(context, locbase, "Path"));
    }

}
