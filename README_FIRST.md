# NV v0.5.1 Clean Build

این نسخه عمداً از صفر ساخته شده و به MapLibre، Compose، Kotlin، Python patch یا فایل‌های نسخه‌های قبلی وابسته نیست.

## بسیار مهم قبل از Upload
در Repository فعلی GitHub، فایل Workflow قدیمی که مرحله
`Fix MapLibre startup initialization`
دارد را حذف کنید.

در پوشه `.github/workflows/` فقط فایل زیر باید باقی بماند:
`build-apk.yml`

## سپس
1. همه فایل‌های این ZIP را در ریشه Repository قرار دهید.
2. Commit کنید.
3. وارد Actions شوید.
4. Workflow با نام `NV Clean APK Build` را اجرا کنید.
5. پس از سبز شدن Build، از Artifacts فایل `NV-v0.5.1-debug-apk` را دانلود کنید.
6. داخل آن `app-debug.apk` قرار دارد.

این APK نسخه‌ی UI/Test است و هنوز نقشه زنده ایران و Valhalla ندارد.
