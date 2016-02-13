package de.haw_hamburg.informatik.sonardev.ws15.treeReconstruction;

import java.util.ArrayList;
import java.util.List;

/**
 * A node in a team formation view (forest), that is, a place (task) or transition (operation).
 * @param <P> The type of parent and child nodes
 */
public abstract class TFVNode<P extends TFVNode> {
    /**
     * NOTE Do not implement equals, the TFV currently is a doubly linked structure, so the reference matters.
     * One might argue that this is a wrong model because of uniqueness; however, we did not discuss the implications
     * of uniqueness in the TFV so far.
     */

    /**
     * The parent of the node (or null if none is linked yet).
     */
    protected P parent;
    /**
     * The children of the node.
     */
    protected List<P> children = new ArrayList<>();

    protected TFVNode(P parent, List<P> children) {
        this.parent = parent;
        if (children != null) {
            this.children = children;
        }
    }

    /**
     * @return The parent of the node or null if none is linked yet
     */
    public P getParent() {
        return parent;
    }

    /**
     * @return The children of the node
     */
    public List<P> getChildren() {
        return children;
    }

    /**
     * @param parent The new parent of the node
     */
    public void setParent(P parent) {
        this.parent = parent;
    }

    /**
     * @param children The new children of the node
     */
    public void setChildren(List<P> children) {
        this.children = children;
    }

    /**
     * Adds a child to the node.
     * @param child A new additional child to be linked with the node
     */
    public void addChild(P child) {
        children.add(child);
    }

    /**
     * @return A string identifying the contents of this node
     */
    public abstract String getContentString();

    /**
     * Continues building a multi-line string representing a forest in StringBuilder
     * @param result The builder that this function appends to
     * @param prefix The prefix of the line that represents this node
     */
    public void buildIndentedForestString(StringBuilder result, String prefix) {
        result.append(prefix);
        result.append("--");
        result.append(getContentString());
        result.append("\n");
        prefix = prefix + "  |";
        for (P child : getChildren()) {
            child.buildIndentedForestString(result, prefix);
        }
    }
}
