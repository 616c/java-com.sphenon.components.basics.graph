// instantiated with jti.pl from ReferenceToMember
// please do not modify this file directly
package com.sphenon.basics.graph.tplinst;

import com.sphenon.basics.graph.*;

import com.sphenon.basics.context.*;
import com.sphenon.basics.exception.*;
import com.sphenon.basics.reference.*;
import com.sphenon.basics.many.*;

public interface ReferenceToMember_TreeNode_long_ReadOnlyVector_TreeNode_long__
  extends Reference_TreeNode_
    , ReferenceToMember<TreeNode,ReadOnlyVector<TreeNode>>
{
    public ReadOnlyVector_TreeNode_long_ getContainer(CallContext context);
    public long     getIndex    (CallContext context);
}
