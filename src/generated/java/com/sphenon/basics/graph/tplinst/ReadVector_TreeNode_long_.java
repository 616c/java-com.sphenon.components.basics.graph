// instantiated with jti.pl from ReadVector
// please do not modify this file directly
package com.sphenon.basics.graph.tplinst;

import com.sphenon.basics.graph.*;

import com.sphenon.basics.context.*;
import com.sphenon.basics.exception.*;

import com.sphenon.basics.many.returncodes.*;

public interface ReadVector_TreeNode_long_
{
    public TreeNode                                    get             (CallContext context, long index) throws DoesNotExist;
    public TreeNode                                    tryGet          (CallContext context, long index);
    public boolean                                     canGet          (CallContext context, long index);

    public ReferenceToMember_TreeNode_long_ReadOnlyVector_TreeNode_long__  getReference    (CallContext context, long index) throws DoesNotExist;
    public ReferenceToMember_TreeNode_long_ReadOnlyVector_TreeNode_long__  tryGetReference (CallContext context, long index);
}

