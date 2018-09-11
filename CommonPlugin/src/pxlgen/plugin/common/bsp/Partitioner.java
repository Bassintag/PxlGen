package pxlgen.plugin.common.bsp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Partitioner.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 10/09/2018
 */
public class Partitioner {

    private int width;
    private int height;
    private int minSize;
    private int maxSize;

    public Partitioner(int width, int height, int minSize, int maxSize) {
        this.width = width;
        this.height = height;
        this.minSize = minSize;
        this.maxSize = maxSize;
    }

    public Leaf[] partition() {
        List<Leaf> leaves = new ArrayList<>();
        Leaf root = new Leaf(0, 0, width, height, minSize);
        leaves.add(root);
        boolean split = true;
        Random random = new Random();
        while (split) {
            split = false;
            Leaf[] tmp = leaves.toArray(new Leaf[0]);
            for (Leaf leaf : tmp) {
                if (leaf.hasSplit()) continue;
                if (leaf.getWidth() > maxSize || leaf.getHeight() > maxSize || random.nextFloat() >= 0.25) {
                    if (!leaf.split()) continue;
                    leaves.add(leaf.getLeft());
                    leaves.add(leaf.getRight());
                    split = true;
                }
            }
        }
        return leaves.stream().filter(l -> !l.hasSplit()).toArray(Leaf[]::new);
    }
}
