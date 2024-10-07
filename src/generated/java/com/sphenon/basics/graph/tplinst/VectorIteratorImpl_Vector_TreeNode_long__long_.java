// instantiated with jti.pl from VectorIteratorImpl
// please do not modify this file directly
package com.sphenon.basics.graph.tplinst;

import com.sphenon.basics.graph.*;

import com.sphenon.basics.context.*;
import com.sphenon.basics.exception.*;

import com.sphenon.basics.many.returncodes.*;

import java.util.Hashtable;

public class VectorIteratorImpl_Vector_TreeNode_long__long_
    implements IteratorItemIndex_Vector_TreeNode_long__long_,
               Cloneable
{
    private ReadOnlyVector_Vector_TreeNode_long__long_ vector;
    private long current_index;

    public VectorIteratorImpl_Vector_TreeNode_long__long_ (CallContext context, ReadOnlyVector_Vector_TreeNode_long__long_ vector) {
        this.vector = vector;
        this.current_index = 0;
    }

    public void     next          (CallContext context) {
        // if (this.current_index < this.vector.getSize(context))
           this.current_index++;
    }

    public long getCurrentIndex (CallContext context) throws DoesNotExist {
        return this.current_index;
    }

    public long tryGetCurrentIndex (CallContext context) {
        return this.current_index;
    }

    public Vector_TreeNode_long_ getCurrent    (CallContext context) throws DoesNotExist {
        return vector.get(context, this.current_index);
    }

    public Vector_TreeNode_long_ tryGetCurrent (CallContext context) {
        return vector.tryGet(context, this.current_index);
    }

    public boolean  canGetCurrent (CallContext context) {
        return vector.canGet(context, this.current_index);
    }

    public Reference_Vector_TreeNode_long__ getReferenceToCurrent (CallContext context) throws DoesNotExist {
        return vector.getReference(context, this.current_index);
    }

    public Reference_Vector_TreeNode_long__ tryGetReferenceToCurrent (CallContext context) {
        return vector.tryGetReference(context, this.current_index);
    }

    public VectorIteratorImpl_Vector_TreeNode_long__long_ clone(CallContext context) {
        try {
            return (VectorIteratorImpl_Vector_TreeNode_long__long_) super.clone();
        } catch (CloneNotSupportedException cnse) { return null; }
    }
}
