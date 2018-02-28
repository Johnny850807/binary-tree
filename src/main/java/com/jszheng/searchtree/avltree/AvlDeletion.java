package com.jszheng.searchtree.avltree;

import com.jszheng.node.TreeNode;
import com.jszheng.searchtree.BinarySearchTree;
import com.jszheng.searchtree.BstDeletion;

public class AvlDeletion<E extends Comparable<? super E>> extends BstDeletion<E> {

    @Override
    protected AvlTree<E> getBt() {
        BinarySearchTree<E> bst = super.getBt();
        return (AvlTree<E>) bst;
    }

    @Override
    protected void fixAfterDeletion(TreeNode<E> parent, TreeNode<E> sibling, TreeNode<E> targetNode, boolean isTargetLeft, int degree) {
        AvlTree<E> avlTree = getBt();
        TreeNode<E> currentNode = parent;

        while (currentNode != null) {
            int bf = avlTree.getBalanceFactor(currentNode);

            if (bf < -1 || bf > 1) {
                TreeNode<E> child = avlTree.getHigherChild(currentNode);
                boolean isGrandChildLeft = avlTree.getHigherChild(child).isLeftChild();
                avlTree.handleUnbalancedNode(currentNode, bf, isGrandChildLeft);
                break;
            }

            currentNode = currentNode.getParent();
        }
    }
}
