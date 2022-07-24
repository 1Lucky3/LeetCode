package problems.LeetCode10;

class Solution {
    static String[] pArray;
    static String[] sArray;

    public boolean isMatch(String s, String p) {
        pArray = p.split("");
        sArray = s.split("");

        if (!p.contains("*") && sArray.length != pArray.length) {
            return false;
        }

        return checkPatterns(0,0,true);
    }

    public static boolean checkPatterns (
            int _sIndex,
            int _i,
            boolean _isMatch
    ) {
        int sIndex = _sIndex;
        int matchesCount = 0;
        int starCharacterIndex = 0;
        int lastStrMatchesIndex = 0;
        int initialMatchesCount = 0;
        boolean isMatch = _isMatch;

        for (int i = _i; i < pArray.length; i++) {
            if (matchesCount == 0) {
                initialMatchesCount = 0;
            }

            if (!isMatch) {
                isMatch = true;
                i++;
                continue;
            }

            if (sIndex == sArray.length) {
                //skip last match and go next
                while (matchesCount > 0) {
                    boolean isTheRightWay = checkPatterns(
                            lastStrMatchesIndex - (initialMatchesCount - matchesCount),
                            starCharacterIndex + 1,
                            true
                    );

                    if (isTheRightWay) {
                        return true;
                    }

                    matchesCount--;
                }

                if (isSkipStarsIfTheyAreLast(i)) {
                    break;
                }

                return starCharacterIndex != 0 && checkPatterns(lastStrMatchesIndex, starCharacterIndex + 1, true);
            }

            if (i + 1 < pArray.length && pArray[i + 1].matches("\\*")) {
                starCharacterIndex = i + 1;

                while (
                        sIndex < sArray.length &&
                                sArray[sIndex].matches(pArray[i])
                ) {
                    matchesCount++;
                    initialMatchesCount++;
                    lastStrMatchesIndex = sIndex;
                    sIndex++;
                }

                i++;
            } else if (!sArray[sIndex++].matches(pArray[i])) {
                //skip last match and go next
                while (matchesCount > 0) {
                    boolean isTheRightWay = checkPatterns(
                            lastStrMatchesIndex - (initialMatchesCount - matchesCount),
                            starCharacterIndex + 1,
                            true
                    );

                    if (isTheRightWay) {
                        return true;
                    }

                    matchesCount--;
                }

                return starCharacterIndex != 0 && checkPatterns(lastStrMatchesIndex, starCharacterIndex + 1, true);
            }
        }

        return sIndex >= sArray.length;
    }

    static boolean isSkipStarsIfTheyAreLast(int counter) {
        int localCounter = counter;

        while (localCounter <= pArray.length) {
            if (localCounter + 1 < pArray.length && pArray[localCounter + 1].matches("\\*")) {
                if (localCounter + 1 == pArray.length - 1) {
                    return true;
                } else {
                    localCounter++;
                    localCounter++;
                }
            } else {
                return false;
            }
        }

        return false;
    }
}
