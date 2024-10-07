// instantiated with jti.pl from VectorIterable
// please do not modify this file directly
package com.sphenon.basics.graph.tplinst;

import com.sphenon.basics.graph.*;

import com.sphenon.basics.context.*;
import com.sphenon.basics.exception.*;

import com.sphenon.basics.many.*;
import com.sphenon.basics.many.returncodes.*;

public class VectorIterable_Vector_TreeNode_long__long_ implements Iterable<Vector_TreeNode_long_>
{
    protected java.util.Iterator<Vector_TreeNode_long_> iterator;

    public VectorIterable_Vector_TreeNode_long__long_ (CallContext context, Vector_Vector_TreeNode_long__long_ vector) {
        this.iterator = (vector == null ? (new java.util.Vector<Vector_TreeNode_long_>()).iterator() : vector.getIterator_Vector_TreeNode_long__(context));
    }

    public java.util.Iterator<Vector_TreeNode_long_> iterator () {
        return this.iterator;
    }
}

