// instantiated with jti.pl from VectorIterable
// please do not modify this file directly
package com.sphenon.basics.graph.tplinst;

import com.sphenon.basics.graph.*;

import com.sphenon.basics.context.*;
import com.sphenon.basics.exception.*;

import com.sphenon.basics.many.*;
import com.sphenon.basics.many.returncodes.*;

public class VectorIterable_TreeNode_long_ implements Iterable<TreeNode>
{
    protected java.util.Iterator<TreeNode> iterator;

    public VectorIterable_TreeNode_long_ (CallContext context, Vector_TreeNode_long_ vector) {
        this.iterator = (vector == null ? (new java.util.Vector<TreeNode>()).iterator() : vector.getIterator_TreeNode_(context));
    }

    public java.util.Iterator<TreeNode> iterator () {
        return this.iterator;
    }
}

