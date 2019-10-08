package nulockvector;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class UnlockVectorDemo<E> {
    private final AtomicReferenceArray<AtomicReferenceArray<E>> buckets;
    private Descriptor descriptor;
    final int N_BUCKET = 30;
    final int FIRST_BUCKET_SIZE = 8;
    final int zeroNumFirst = 28;

    public UnlockVectorDemo() {
        buckets = new AtomicReferenceArray<AtomicReferenceArray<E>>(N_BUCKET);
        buckets.set(0, new AtomicReferenceArray<E>(FIRST_BUCKET_SIZE));
       // descriptor = new AtomicReference<Descriptor<E>>(new Descriptor<E>(0, null));
    }

    public void push_back(E E) {
        Descriptor<E> desc;
        Descriptor<E> newd;



    }


    class Descriptor<E> {
        public int size;

        volatile WriteDescriptor<E> writeop;

        public Descriptor(int size, WriteDescriptor<E> writeop) {
            this.size = size;
            this.writeop = writeop;
        }

        public void completeWrite() {
            WriteDescriptor<E> tempOP = writeop;
            if (tempOP != null) {
                tempOP.doIt();
                writeop = null;
            }
        }

//        public E get(int index) {
//            int pos = index + FIRST_BUCKET_SIZE;
//            int zeroNumpos = Integer.numberOfLeadingZeros(pos);
//            int bucketInd = zeroNumFirst - zeroNumpos;
//            int idx = (0x80000000 >>> zeroNumpos) ^ pos;
//            return buckets.get(bucketInd).get(idx);
//
//        }

    }

    class WriteDescriptor<E> {
        public E oldV;
        public E newV;
        public AtomicReferenceArray<E> addr;
        public int addr_ind;

        public WriteDescriptor(AtomicReferenceArray<E> addr, E oldV, E newV, int addr_ind) {
            this.addr = addr;
            this.oldV = oldV;
            this.newV = newV;
            this.addr_ind = addr_ind;
        }

        public void doIt() {
            addr.compareAndSet(addr_ind, oldV, newV);
        }
    }
}