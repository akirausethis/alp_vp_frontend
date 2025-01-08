-- CreateTable
CREATE TABLE "users" (
    "id" SERIAL NOT NULL,
    "username" VARCHAR(100) NOT NULL,
    "email" VARCHAR(150) NOT NULL,
    "password" VARCHAR(100) NOT NULL,
    "token" VARCHAR(100),
    "profile" VARCHAR(100),
    "cv" VARCHAR(100),

    CONSTRAINT "users_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "panitias" (
    "id" SERIAL NOT NULL,
    "organisasi" VARCHAR(100) NOT NULL,
    "title" VARCHAR(100) NOT NULL,
    "description" TEXT NOT NULL,
    "start_date" VARCHAR(100) NOT NULL,
    "poster" VARCHAR(100),
    "user_id" INTEGER NOT NULL,

    CONSTRAINT "panitias_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "surveys" (
    "id" SERIAL NOT NULL,
    "title" VARCHAR(100) NOT NULL,
    "start_date" VARCHAR(100) NOT NULL,
    "end_date" VARCHAR(100) NOT NULL,

    CONSTRAINT "surveys_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "kps" (
    "id" SERIAL NOT NULL,
    "date_start" TIMESTAMP(3) NOT NULL,
    "description" TEXT NOT NULL,
    "type_id" INTEGER NOT NULL,

    CONSTRAINT "kps_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "historys" (
    "id" SERIAL NOT NULL,
    "panitia_id" INTEGER NOT NULL,
    "user_id" INTEGER NOT NULL,
    "kp_id" INTEGER NOT NULL,

    CONSTRAINT "historys_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "types" (
    "id" SERIAL NOT NULL,
    "type" VARCHAR(100) NOT NULL,

    CONSTRAINT "types_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "questions" (
    "id" SERIAL NOT NULL,
    "question" VARCHAR(100) NOT NULL,
    "survey_id" INTEGER NOT NULL,

    CONSTRAINT "questions_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "feedbacks" (
    "id" SERIAL NOT NULL,
    "question_id" INTEGER NOT NULL,
    "feedback" VARCHAR(100) NOT NULL,
    "user_id" INTEGER NOT NULL,

    CONSTRAINT "feedbacks_pkey" PRIMARY KEY ("id")
);

-- CreateIndex
CREATE UNIQUE INDEX "users_email_key" ON "users"("email");

-- AddForeignKey
ALTER TABLE "panitias" ADD CONSTRAINT "panitias_user_id_fkey" FOREIGN KEY ("user_id") REFERENCES "users"("id") ON DELETE CASCADE ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "kps" ADD CONSTRAINT "kps_type_id_fkey" FOREIGN KEY ("type_id") REFERENCES "types"("id") ON DELETE CASCADE ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "historys" ADD CONSTRAINT "historys_user_id_fkey" FOREIGN KEY ("user_id") REFERENCES "users"("id") ON DELETE CASCADE ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "historys" ADD CONSTRAINT "historys_panitia_id_fkey" FOREIGN KEY ("panitia_id") REFERENCES "panitias"("id") ON DELETE CASCADE ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "historys" ADD CONSTRAINT "historys_kp_id_fkey" FOREIGN KEY ("kp_id") REFERENCES "kps"("id") ON DELETE CASCADE ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "questions" ADD CONSTRAINT "questions_survey_id_fkey" FOREIGN KEY ("survey_id") REFERENCES "surveys"("id") ON DELETE CASCADE ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "feedbacks" ADD CONSTRAINT "feedbacks_user_id_fkey" FOREIGN KEY ("user_id") REFERENCES "users"("id") ON DELETE CASCADE ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "feedbacks" ADD CONSTRAINT "feedbacks_question_id_fkey" FOREIGN KEY ("question_id") REFERENCES "questions"("id") ON DELETE CASCADE ON UPDATE CASCADE;
