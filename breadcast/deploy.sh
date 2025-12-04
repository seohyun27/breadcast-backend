#!/bin/bash

# 1. 실행 중인 서버 죽이기
echo "Checking for running application..."
CURRENT_PID=$(pgrep -f "breadcast-.*.jar")

if [ -z "$CURRENT_PID" ]; then
  echo "No running application found."
else
  echo "Found running application with PID: $CURRENT_PID"
  echo "Killing process..."
  kill -9 $CURRENT_PID
  sleep 2
fi

# 2. 새 서버 실행 (입출력 완전 분리)
echo "Deploying new application..."

# [핵심] 
# 1. nohup으로 백그라운드 실행
# 2. 입력을 /dev/null로 돌려서 터미널 연결 끊기 (< /dev/null)
# 3. RUNNER_TRACKING_ID를 비워서 추적 끊기
# 4. disown으로 쉘의 작업 목록에서 지워버리기
export RUNNER_TRACKING_ID=""
nohup java -jar build/libs/*SNAPSHOT.jar > nohup.out 2>&1 < /dev/null & disown

echo "Deployment command executed."