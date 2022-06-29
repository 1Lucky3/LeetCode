package problems.LeetCode4;

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0) {
            return getMedian(nums2);
        }
        if (nums2.length == 0) {
            return getMedian(nums1);
        }

        int[] biggestArr;
        int[] lessArr;

        int[] result = new int[nums1.length + nums2.length];

        if (nums1[0] > nums2[0]) {
            result[0] = nums2[0];
            biggestArr = nums1;
            lessArr = nums2;
        } else {
            result[0] = nums1[0];
            biggestArr = nums2;
            lessArr = nums1;
        }

        int resultNumberIndex = 1;
        int lessArrIndex = 1;

        for (int i = 0; i < biggestArr.length; i++) {
            while (
                lessArrIndex < lessArr.length &&
                biggestArr[i] > lessArr[lessArrIndex]
            ) {
                result[resultNumberIndex++] = lessArr[lessArrIndex++];
            }

            result[resultNumberIndex++] = biggestArr[i];
        }

        while (lessArrIndex < lessArr.length) {
            result[resultNumberIndex++] = lessArr[lessArrIndex++];
        }


        return getMedian(result);
    }

    public static double getMedian(int[] result) {
        int length = result.length;
        if (length % 2 == 0) {
            return (double) (result[(length/2) - 1] + result[(length / 2)])/2;
        } else {
            return result[(length/2)];
        }
    }
}