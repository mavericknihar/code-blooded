class Solution
{
    //Function to find a continuous sub-array which adds up to a given number.
    static ArrayList<Integer> subarraySum(int[] arr, int n, int s) 
    {
        int i=0,j=0;
        int curSum=arr[0];
        ArrayList<Integer> list=new ArrayList<>();
        if(s==0)
        {
            list.add(-1);
            return list;
        }
        while(j<n)
        {
            if(curSum==s)
            {
                list.add(i+1);
                list.add(j+1);
                return list;
            }
            else if(curSum<s)
            {
                j++;
                if(j>=n) break;
                curSum=curSum+arr[j];
            }
            else
            {
                curSum=curSum-arr[i];
                i++;
            }
        }
        list.add(-1);
        return list;
    }
}
