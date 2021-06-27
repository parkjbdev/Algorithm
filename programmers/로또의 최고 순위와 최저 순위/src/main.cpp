//
// Created by parkj on 2021-05-06.
//

#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> solution(vector<int> lottos, vector<int> win_nums);

int main (void)
{
	vector<int> lottos[] = {{44, 1, 0, 0, 31, 25}, {0, 0, 0, 0, 0, 0}, {45, 4, 35, 20, 3, 9}};
	vector<int> win_nums[] = {{31, 10, 45, 1, 6, 19}, {38, 19, 20, 40, 15, 25}, {20, 9, 3, 45, 4, 35}};
	for (int i = 0; i < 3; ++i)
	{
		cout << "lottos: ";
		for (int j = 0; j < lottos[i].size(); ++j)	cout << lottos[i][j] << " ";
		cout << endl;

		cout << "win_nums: ";
		for (int j = 0; j < win_nums[i].size(); ++j)	cout << win_nums[i][j] << " ";
		cout << endl;

		vector<int> answer = solution(lottos[i], win_nums[i]);
		for (int j = 0; j < answer.size(); ++j)	cout << answer[j] << " ";
		cout << endl;
	}
}

int getPlace(int score)
{
	int place = 7 - score;
	if (place > 6)  place = 6;

	return place;
}

vector<int> solution(vector<int> lottos, vector<int> win_nums)
{
	int match_cnt = 0, unknown_cnt = 0;

	for (int i = 0; i < 6; ++i)
	{
		if (lottos[i] == 0) unknown_cnt++;
		else if(find(win_nums.begin(), win_nums.end(), lottos[i]) != win_nums.end())    match_cnt++;
	}

	const int bestCase = getPlace(match_cnt + unknown_cnt);
	const int worstCase = getPlace(match_cnt);

	vector<int> answer;
	answer.push_back(bestCase);
	answer.push_back(worstCase);
	return answer;
}