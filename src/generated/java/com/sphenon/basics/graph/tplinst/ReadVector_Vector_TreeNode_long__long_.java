// instantiated with jti.pl from ReadVector
// please do not modify this file directly
package com.sphenon.basics.graph.tplinst;

import com.sphenon.basics.graph.*;

import com.sphenon.basics.context.*;
import com.sphenon.basics.exception.*;

import com.sphenon.basics.many.returncodes.*;

public interface ReadVector_Vector_TreeNode_long__long_
{
    public Vector_TreeNode_long_                                    get             (CallContext context, long index) throws DoesNotExist;
    public Vector_TreeNode_long_                                    tryGet          (CallContext context, long index);
    public boolean                                     canGet          (CallContext context, long index);

    public ReferenceToMember_Vector_TreeNode_long__long_ReadOnlyVector_Vector_TreeNode_long__long__  getReference    (CallContext context, long index) throws DoesNotExist;
    public ReferenceToMember_Vector_TreeNode_long__long_ReadOnlyVector_Vector_TreeNode_long__long__  tryGetReference (CallContext context, long index);
}

