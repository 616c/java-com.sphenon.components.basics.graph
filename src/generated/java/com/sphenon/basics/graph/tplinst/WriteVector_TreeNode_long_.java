// instantiated with jti.pl from WriteVector
// please do not modify this file directly
package com.sphenon.basics.graph.tplinst;

import com.sphenon.basics.graph.*;

import com.sphenon.basics.context.*;
import com.sphenon.basics.exception.*;

import com.sphenon.basics.many.returncodes.*;

public interface WriteVector_TreeNode_long_
{
    public TreeNode set          (CallContext context, long index, TreeNode item);
    public void     add          (CallContext context, long index, TreeNode item) throws AlreadyExists;
    public void     prepend      (CallContext context, TreeNode item);
    public void     append       (CallContext context, TreeNode item);
    public void     insertBefore (CallContext context, long index, TreeNode item) throws DoesNotExist;
    public void     insertBehind (CallContext context, long index, TreeNode item) throws DoesNotExist;
    public TreeNode replace      (CallContext context, long index, TreeNode item) throws DoesNotExist;
    public TreeNode unset        (CallContext context, long index);
    public TreeNode remove       (CallContext context, long index) throws DoesNotExist;
}

